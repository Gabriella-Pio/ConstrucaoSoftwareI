package com.gabriella.barbearia.service;

import com.gabriella.barbearia.dto.AgendamentoDTO;
import com.gabriella.barbearia.model.*;
import com.gabriella.barbearia.model.enums.Status;
import com.gabriella.barbearia.model.enums.DiaSemana;
import com.gabriella.barbearia.repository.AgendamentoRepository;
import com.gabriella.barbearia.repository.BarbeiroRepository;
import com.gabriella.barbearia.repository.ClienteRepository;
import com.gabriella.barbearia.repository.ServicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.util.Optional;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class AgendamentoService {

  private final AgendamentoRepository agendamentoRepository;
  private final BarbeiroRepository barbeiroRepository;
  private final ClienteRepository clienteRepository;
  private final ServicoRepository servicoRepository;

  public AgendamentoService(AgendamentoRepository agendamentoRepository,
      BarbeiroRepository barbeiroRepository,
      ClienteRepository clienteRepository,
      ServicoRepository servicoRepository) {
    this.agendamentoRepository = agendamentoRepository;
    this.barbeiroRepository = barbeiroRepository;
    this.clienteRepository = clienteRepository;
    this.servicoRepository = servicoRepository;
  }

  public Page<AgendamentoDTO> findAll(Pageable pageable) {
    return agendamentoRepository.findAll(pageable).map(AgendamentoDTO::fromEntity);
  }

  public AgendamentoDTO findById(UUID id) {
    Agendamento agendamento = agendamentoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));
    return AgendamentoDTO.fromEntity(agendamento);
  }

  @Transactional
  public AgendamentoDTO criarAgendamento(AgendamentoDTO dto) {
    Cliente cliente = clienteRepository.findById(dto.clienteId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    Barbeiro barbeiro = barbeiroRepository.findById(dto.barbeiroId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Barbeiro não encontrado"));

    Servico servico = servicoRepository.findById(dto.servicoId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));

    LocalDateTime inicio = dto.dataHora();
    LocalDateTime fim = inicio.plusMinutes(servico.getDuracaoMinutos());

    validarExpediente(barbeiro, inicio, fim);
    validarConflitoHorario(barbeiro, inicio, fim, null);

    Agendamento agendamento = new Agendamento();
    agendamento.setDataHora(inicio);
    agendamento.setStatus(Status.AGENDADO);
    agendamento.setCliente(cliente);
    agendamento.setBarbeiro(barbeiro);
    agendamento.setServico(servico);

    return AgendamentoDTO.fromEntity(agendamentoRepository.save(agendamento));
  }

  // Altera apenas a Data e Hora.
  @Transactional
  public AgendamentoDTO reprogramar(UUID id, LocalDateTime novaDataHora) {
    Agendamento agendamento = agendamentoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));

    LocalDateTime novoFim = novaDataHora.plusMinutes(agendamento.getServico().getDuracaoMinutos());

    validarExpediente(agendamento.getBarbeiro(), novaDataHora, novoFim);

    validarConflitoHorario(agendamento.getBarbeiro(), novaDataHora, novoFim, id);

    agendamento.setDataHora(novaDataHora);
    return AgendamentoDTO.fromEntity(agendamentoRepository.save(agendamento));
  }

  // Altera apenas o Status (Concluído, Cancelado, etc).
  @Transactional
  public AgendamentoDTO atualizarStatus(UUID id, Status novoStatus) {
    Agendamento agendamento = agendamentoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));

    agendamento.setStatus(novoStatus);
    return AgendamentoDTO.fromEntity(agendamentoRepository.save(agendamento));
  }

  @Transactional
  public void delete(UUID id) {
    Agendamento agendamento = agendamentoRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agendamento não encontrado"));
    agendamentoRepository.delete(agendamento);
  }

  // --- Métodos de Validação Internos ---

  private void validarExpediente(Barbeiro barbeiro, LocalDateTime inicio, LocalDateTime fim) {
    DayOfWeek diaSemanaSolicitado = inicio.getDayOfWeek();
    DiaSemana diaSemanaEnum = converterParaDiaSemana(diaSemanaSolicitado);
    LocalTime horaInicio = inicio.toLocalTime();
    LocalTime horaFim = fim.toLocalTime();

    boolean estaNoExpediente = barbeiro.getHorariosTrabalho().stream()
        .anyMatch(h -> h.getDiaSemana() == diaSemanaEnum &&
            !horaInicio.isBefore(h.getHoraInicio()) &&
            !horaFim.isAfter(h.getHoraFim()));

    if (!estaNoExpediente) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O barbeiro não atende neste dia ou horário.");
    }
  }

  private DiaSemana converterParaDiaSemana(DayOfWeek dayOfWeek) {
    return switch (dayOfWeek) {
      case MONDAY -> DiaSemana.SEGUNDA;
      case TUESDAY -> DiaSemana.TERCA;
      case WEDNESDAY -> DiaSemana.QUARTA;
      case THURSDAY -> DiaSemana.QUINTA;
      case FRIDAY -> DiaSemana.SEXTA;
      case SATURDAY -> DiaSemana.SABADO;
      case SUNDAY -> DiaSemana.DOMINGO;
    };
  }

  private void validarConflitoHorario(Barbeiro barbeiro, LocalDateTime novoInicio, LocalDateTime novoFim,
      UUID agendamentoIdAtual) {
    List<Agendamento> agendamentosExistentes = agendamentoRepository.findByBarbeiro(barbeiro);

    for (Agendamento existente : agendamentosExistentes) {
      if (agendamentoIdAtual != null && existente.getId().equals(agendamentoIdAtual)) {
        continue;
      }

      LocalDateTime extInicio = existente.getDataHora();
      LocalDateTime extFim = extInicio.plusMinutes(existente.getServico().getDuracaoMinutos());

      if (novoInicio.isBefore(extFim) && extInicio.isBefore(novoFim)) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "O barbeiro já possui um cliente neste horário.");
      }
    }
  }
}