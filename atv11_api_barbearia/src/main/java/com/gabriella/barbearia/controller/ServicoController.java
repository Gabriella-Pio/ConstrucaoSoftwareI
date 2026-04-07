package com.gabriella.barbearia.controller;

import com.gabriella.barbearia.dto.ServicoDTO;
import com.gabriella.barbearia.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

  private final ServicoService service;

  public ServicoController(ServicoService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Page<ServicoDTO>> listarTodos(Pageable pageable) {
    return ResponseEntity.ok(service.findAll(pageable));
  }

  @PostMapping
  public ResponseEntity<ServicoDTO> cadastrar(@RequestBody @Valid ServicoDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ServicoDTO> atualizar(@PathVariable UUID id, @RequestBody @Valid ServicoDTO dto) {
    return ResponseEntity.ok(service.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletar(@PathVariable UUID id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}