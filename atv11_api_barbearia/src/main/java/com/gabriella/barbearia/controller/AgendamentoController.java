package com.gabriella.barbearia.controller;

import com.gabriella.barbearia.dto.AgendamentoDTO;
import com.gabriella.barbearia.model.enums.Status;
import com.gabriella.barbearia.service.AgendamentoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<AgendamentoDTO>> listarTodos(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<AgendamentoDTO> criar(@RequestBody @Valid AgendamentoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criarAgendamento(dto));
    }

    @PatchMapping("/{id}/reprogramar")
    public ResponseEntity<AgendamentoDTO> reprogramar(@PathVariable UUID id, @RequestBody LocalDateTime novaDataHora) {
        return ResponseEntity.ok(service.reprogramar(id, novaDataHora));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<AgendamentoDTO> atualizarStatus(@PathVariable UUID id, @RequestBody Status novoStatus) {
        return ResponseEntity.ok(service.atualizarStatus(id, novoStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}