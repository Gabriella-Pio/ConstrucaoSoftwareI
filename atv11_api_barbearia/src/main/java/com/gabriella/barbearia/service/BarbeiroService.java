package com.gabriella.barbearia.service;

import com.gabriella.barbearia.dto.BarbeiroDTO;
import com.gabriella.barbearia.model.Barbeiro;
import com.gabriella.barbearia.repository.BarbeiroRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BarbeiroService {

  private final BarbeiroRepository barbeiroRepository;

  public BarbeiroService(BarbeiroRepository barbeiroRepository) {
    this.barbeiroRepository = barbeiroRepository;
  }

  public Page<BarbeiroDTO> findAll(Pageable pageable) {
    return barbeiroRepository.findAll(pageable)
        .map(BarbeiroDTO::fromEntity);
  }

  public Barbeiro findByIdInternal(UUID id) {
    return barbeiroRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Barbeiro não encontrado"));
  }

  public BarbeiroDTO findById(UUID id) {
    return BarbeiroDTO.fromEntity(findByIdInternal(id));
  }

  @Transactional
  public BarbeiroDTO save(BarbeiroDTO dto) {
    Barbeiro barbeiro = dto.toEntity();
    return BarbeiroDTO.fromEntity(barbeiroRepository.save(barbeiro));
  }

  @Transactional
  public BarbeiroDTO update(UUID id, BarbeiroDTO dto) {
    Barbeiro existing = findByIdInternal(id);
    existing.setNome(dto.nome());
    existing.setEspecialidade(dto.especialidade());
    return BarbeiroDTO.fromEntity(barbeiroRepository.save(existing));
  }

  @Transactional
  public void delete(UUID id) {
    Barbeiro barbeiro = findByIdInternal(id);
    barbeiroRepository.delete(barbeiro);
  }
}