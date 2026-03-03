package com.cupons.cupons.service;

import com.cupons.cupons.domain.Coupon;
import com.cupons.cupons.domain.CouponType;
import com.cupons.cupons.dto.*;
import com.cupons.cupons.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CouponService {

  private final CouponRepository repository;

  @Value("${service.feePercent:0}")
  private BigDecimal feePercentage;

  public CouponService(CouponRepository repository) {
    this.repository = repository;
  }

  // CRUD
  // criar
  public CouponResponseDTO create(CouponRequestDTO dto) {
    validarDadosCupom(dto);

    // código único - 409 Conflito
    if (repository.existsByCodeIgnoreCase(dto.getCode())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          "Já existe um cupom com o código: " + dto.getCode().toUpperCase());
    }

    Coupon coupon = toEntity(dto);
    return CouponResponseDTO.from(repository.save(coupon));
  }

  // listar todos
  public List<CouponResponseDTO> listAll() {
    return repository.findAll()
        .stream()
        .map(CouponResponseDTO::from)
        .toList();
  }

  // buscar por ID
  public CouponResponseDTO getById(Long id) {
    return CouponResponseDTO.from(findOrThrow(id));
  }

  // atualizar
  public CouponResponseDTO update(Long id, CouponRequestDTO dto) {
    Coupon existente = findOrThrow(id);
    validarDadosCupom(dto);

    // Verifica conflito de código somente se o código foi alterado
    String novoCode = dto.getCode().trim().toUpperCase();
    if (!novoCode.equals(existente.getCode()) && repository.existsByCodeIgnoreCase(novoCode)) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          "Já existe um cupom com o código: " + novoCode);
    }

    // Atualiza os campos na entidade existente (mantém o mesmo ID e usedCount)
    atualizarEntidade(existente, dto);
    return CouponResponseDTO.from(repository.save(existente));
  }

  // remover
  public void delete(Long id) {
    Coupon coupon = findOrThrow(id);

    // pode deletar se usedCount == 0 - 409
    if (coupon.getUsedCount() > 0) {
      throw new ResponseStatusException(HttpStatus.CONFLICT,
          "Não é possível remover um cupom que já foi utilizado (usedCount=" + coupon.getUsedCount() + ").");
    }

    repository.delete(coupon);
  }

  // Aplicação do cupom - regra principal
  public ApplyCouponResponseDTO apply(ApplyCouponRequestDTO request) {
    if (request.getCode() == null || request.getCartValue() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código e valor do carrinho são obrigatórios.");
    }

    // Busca pelo código (case-insensitive)
    Coupon coupon = repository.findByCodeIgnoreCase(request.getCode())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Cupom não encontrado: " + request.getCode()));

    OffsetDateTime now = OffsetDateTime.now();

    // ativo e dentro da vigência - 422
    if (!coupon.getActive() || !coupon.isWithinValidPeriod(now)) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
          "Cupom inativo ou fora do período de vigência.");
    }

    // valor mínimo do carrinho - 422
    if (request.getCartValue().compareTo(coupon.getMinCartValue()) < 0) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
          "Valor do carrinho abaixo do mínimo exigido de " + coupon.getMinCartValue() + ".");
    }

    // limite de uso - 422
    if (coupon.hasReachedUsageLimit()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
          "Cupom atingiu o limite máximo de usos.");
    }

    // Taxa de serviço - configurável via application.yml
    BigDecimal cartValue = request.getCartValue();
    BigDecimal serviceFee = cartValue.multiply(feePercentage)
        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    BigDecimal cartValueWithFee = cartValue.add(serviceFee);

    // Cálculo do desconto sobre o valor com taxa
    BigDecimal discount = calcularDesconto(coupon, cartValueWithFee);

    // valorFinal não pode ser negativo
    BigDecimal finalValue = cartValueWithFee.subtract(discount).max(BigDecimal.ZERO);

    return new ApplyCouponResponseDTO(
        coupon.getCode(),
        cartValue,
        serviceFee,
        cartValueWithFee,
        discount,
        finalValue);
  }

  // Consumir cupom (incrementar uso) — opcional conforme PDF
  public void consume(Long id) {
    Coupon coupon = findOrThrow(id);

    // Valida antes de incrementar
    OffsetDateTime now = OffsetDateTime.now();
    if (!coupon.getActive() || !coupon.isWithinValidPeriod(now)) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
          "Cupom inativo ou fora do período de vigência.");
    }
    if (coupon.hasReachedUsageLimit()) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
          "Cupom atingiu o limite máximo de usos.");
    }

    coupon.incrementUsage();
    repository.save(coupon);
  }


  // Helpers privados
  // Recupera a entidade ou lança 404
  private Coupon findOrThrow(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Cupom não encontrado com ID: " + id));
  }

  // Valida as regras de criação/edição do cupom - lança 400
  private void validarDadosCupom(CouponRequestDTO dto) {
    if (dto.getCode() == null || dto.getCode().trim().isEmpty()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O código do cupom é obrigatório.");
    }
    if (dto.getType() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O tipo do cupom é obrigatório.");
    }
    if (dto.getStartAt() == null || dto.getEndAt() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "As datas de vigência são obrigatórias.");
    }
    // inicioVigencia deve ser antes de fimVigencia → 400
    if (!dto.getStartAt().isBefore(dto.getEndAt())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "A data de início deve ser anterior à data de fim da vigência.");
    }

    if (dto.getType() == CouponType.PERCENT) {
      // valor entre 0,01 e 100,00
      if (dto.getValue() == null
          || dto.getValue().compareTo(new BigDecimal("0.01")) < 0
          || dto.getValue().compareTo(new BigDecimal("100.00")) > 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Para cupons PERCENT, o valor deve estar entre 0,01 e 100,00.");
      }
      // descontoMaximo obrigatório e > 0
      if (dto.getMaxDiscount() == null || dto.getMaxDiscount().compareTo(BigDecimal.ZERO) <= 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Para cupons PERCENT, o desconto máximo (maxDiscount) é obrigatório e deve ser > 0.");
      }
    } else if (dto.getType() == CouponType.FIXED) {
      // valor > 0
      if (dto.getValue() == null || dto.getValue().compareTo(BigDecimal.ZERO) <= 0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Para cupons FIXED, o valor deve ser > 0.");
      }
      // descontoMaximo deve ser nulo
      if (dto.getMaxDiscount() != null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Para cupons FIXED, o campo maxDiscount deve ser nulo.");
      }
    }

    // valorMinimoCarrinho >= 0
    if (dto.getMinCartValue() != null && dto.getMinCartValue().compareTo(BigDecimal.ZERO) < 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "O valor mínimo do carrinho deve ser >= 0.");
    }

    // limiteDeUsos > 0 quando informado
    if (dto.getUsageLimit() != null && dto.getUsageLimit() <= 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
          "O limite de usos deve ser > 0 quando informado.");
    }
  }

  private BigDecimal calcularDesconto(Coupon coupon, BigDecimal cartValueWithFee) {
    if (coupon.getType() == CouponType.FIXED) {
      // desconto = min(valor, valorCarrinho) - nunca desconta mais do que o carrinho vale
      return coupon.getValue().min(cartValueWithFee);
    } else {
      // PERCENT: desconto = carrinho * (valor/100), limitado por descontoMaximo
      BigDecimal desconto = cartValueWithFee
          .multiply(coupon.getValue())
          .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
      return desconto.min(coupon.getMaxDiscount());
    }
  }

  private Coupon toEntity(CouponRequestDTO dto) {
    Coupon coupon = new Coupon();
    coupon.setCode(dto.getCode());
    coupon.setType(dto.getType());
    coupon.setValue(dto.getValue());
    coupon.setMinCartValue(dto.getMinCartValue() != null ? dto.getMinCartValue() : BigDecimal.ZERO);
    coupon.setMaxDiscount(dto.getMaxDiscount());
    coupon.setStartAt(dto.getStartAt());
    coupon.setEndAt(dto.getEndAt());
    coupon.setUsageLimit(dto.getUsageLimit());
    coupon.setActive(dto.getActive() != null ? dto.getActive() : true);
    coupon.setUsedCount(0);
    return coupon;
  }

  private void atualizarEntidade(Coupon coupon, CouponRequestDTO dto) {
    coupon.setCode(dto.getCode());
    coupon.setType(dto.getType());
    coupon.setValue(dto.getValue());
    coupon.setMinCartValue(dto.getMinCartValue() != null ? dto.getMinCartValue() : BigDecimal.ZERO);
    coupon.setMaxDiscount(dto.getMaxDiscount());
    coupon.setStartAt(dto.getStartAt());
    coupon.setEndAt(dto.getEndAt());
    coupon.setUsageLimit(dto.getUsageLimit());
    coupon.setActive(dto.getActive() != null ? dto.getActive() : true);
  }
}
