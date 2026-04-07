package com.gabriella.barbearia.service;

import com.gabriella.barbearia.dto.HorarioTrabalhoDTO;
import com.gabriella.barbearia.model.Barbeiro;
import com.gabriella.barbearia.model.HorarioTrabalho;
import com.gabriella.barbearia.repository.BarbeiroRepository;
import com.gabriella.barbearia.repository.HorarioTrabalhoRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class HorarioTrabalhoService {

  private final HorarioTrabalhoRepository horarioRepository;
  private final BarbeiroRepository barbeiroRepository;

  public HorarioTrabalhoService(HorarioTrabalhoRepository horarioRepository,
      BarbeiroRepository barbeiroRepository) {
    this.horarioRepository = horarioRepository;
    this.barbeiroRepository = barbeiroRepository;
  }

  @Transactional
  public HorarioTrabalhoDTO save(HorarioTrabalhoDTO dto) {
    Barbeiro barbeiro = barbeiroRepository.findById(dto.barbeiroId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Barbeiro não encontrado"));

    HorarioTrabalho horario = new HorarioTrabalho();
    horario.setDiaSemana(dto.diaSemana());
    horario.setHoraInicio(dto.horaInicio());
    horario.setHoraFim(dto.horaFim());
    horario.setBarbeiro(barbeiro);

    return HorarioTrabalhoDTO.fromEntity(horarioRepository.save(horario));
  }

  @Transactional
  public void delete(UUID id) {
    if (!horarioRepository.existsById(id)) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Horário não encontrado");
    }
    horarioRepository.deleteById(id);
  }
}