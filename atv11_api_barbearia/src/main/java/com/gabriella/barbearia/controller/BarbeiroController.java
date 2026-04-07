package com.gabriella.barbearia.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gabriella.barbearia.dto.BarbeiroDTO;
import com.gabriella.barbearia.model.Barbeiro;
import com.gabriella.barbearia.service.BarbeiroService;

import java.util.UUID;

@RestController
@RequestMapping("/api/barbeiros")
public class BarbeiroController {

  private final BarbeiroService barbeiroService;

  public BarbeiroController(BarbeiroService barbeiroService) {
    this.barbeiroService = barbeiroService;
  }

  @PostMapping
  public ResponseEntity<BarbeiroDTO> cadastrar(@Valid @RequestBody BarbeiroDTO dto) {
    BarbeiroDTO created = barbeiroService.save(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  @GetMapping
  public ResponseEntity<Page<BarbeiroDTO>> listar(Pageable pageable) {
    return ResponseEntity.ok(barbeiroService.findAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<BarbeiroDTO> buscarPorId(@PathVariable UUID id) {
    return ResponseEntity.ok(barbeiroService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<BarbeiroDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody BarbeiroDTO dto) {
    return ResponseEntity.ok(barbeiroService.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable UUID id) {
    barbeiroService.delete(id);
    return ResponseEntity.noContent().build();
  }
}