package com.gabriella.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriella.barbearia.model.Agendamento;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import com.gabriella.barbearia.model.Barbeiro;

public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {
  // Busca agendamentos de um barbeiro em uma data específica
  List<Agendamento> findByBarbeiroAndDataHoraBetween(
      Barbeiro barbeiro,
      LocalDateTime start,
      LocalDateTime end);

  // Busca todos os agendamentos de um barbeiro
  List<Agendamento> findByBarbeiro(Barbeiro barbeiro);
}