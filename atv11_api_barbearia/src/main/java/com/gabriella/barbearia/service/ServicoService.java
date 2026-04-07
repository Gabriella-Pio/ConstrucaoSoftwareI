package com.gabriella.barbearia.service;

import com.gabriella.barbearia.dto.ServicoDTO;
import com.gabriella.barbearia.model.Servico;
import com.gabriella.barbearia.repository.ServicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class ServicoService {

  private final ServicoRepository servicoRepository;

  public ServicoService(ServicoRepository servicoRepository) {
    this.servicoRepository = servicoRepository;
  }

  public Page<ServicoDTO> findAll(Pageable pageable) {
    return servicoRepository.findAll(pageable)
        .map(ServicoDTO::fromEntity);
  }

  public Servico findByIdInternal(UUID id) {
    return servicoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));
  }

  @Transactional
  public ServicoDTO save(ServicoDTO dto) {
    Servico servico = dto.toEntity();
    return ServicoDTO.fromEntity(servicoRepository.save(servico));
  }

  @Transactional
  public ServicoDTO update(UUID id, ServicoDTO dto) {
    Servico existing = findByIdInternal(id);
    existing.setNome(dto.nome());
    existing.setPreco(dto.preco());
    existing.setDuracaoMinutos(dto.duracaoMinutos());
    return ServicoDTO.fromEntity(servicoRepository.save(existing));
  }

  @Transactional
  public void delete(UUID id) {
    Servico servico = findByIdInternal(id);
    servicoRepository.delete(servico);
  }
}