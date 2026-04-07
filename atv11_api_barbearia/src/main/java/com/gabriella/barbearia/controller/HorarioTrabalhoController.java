package com.gabriella.barbearia.controller;

import com.gabriella.barbearia.dto.HorarioTrabalhoDTO;
import com.gabriella.barbearia.service.HorarioTrabalhoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/horarios-trabalho")
public class HorarioTrabalhoController {

    private final HorarioTrabalhoService service;

    public HorarioTrabalhoController(HorarioTrabalhoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HorarioTrabalhoDTO> cadastrar(@RequestBody @Valid HorarioTrabalhoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}