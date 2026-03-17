package com.luizegabriella.reserva.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luizegabriella.reserva.entity.Reserva;
import com.luizegabriella.reserva.entity.Status;
import com.luizegabriella.reserva.entity.TipoQuarto;
import com.luizegabriella.reserva.service.ReservaService;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public List<Reserva> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<Reserva> salvar(@RequestBody @Valid Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(reserva));
    }

    @GetMapping("/{id}")
    public Reserva findById(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Reserva update(@PathVariable Long id, @RequestBody Reserva reserva) {
        return service.atualizar(id, reserva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deletar(id);
    }

    // Filtros

    @GetMapping("/status/{status}")
    public List<Reserva> findByStatus(@PathVariable Status status) {
        return service.buscarPorStatus(status);
    }

    @GetMapping("/tipo_quarto/{tipoQuarto}")
    public List<Reserva> findByTipoQuarto(@PathVariable TipoQuarto tipoQuarto) {
        return service.buscarPorTipo(tipoQuarto);
    }

    @GetMapping("/hoje")
    public List<Reserva> reservasHoje() {
        return service.reservasHoje();
    }

    @GetMapping("/proximas")
    public List<Reserva> reservasProximas(@RequestParam(defaultValue = "7") int dias) {
        return service.proximasReservas(dias);
    }

    @GetMapping("/buscar")
    public List<Reserva> buscar(@RequestParam String termo) {
        return service.buscar(termo);
    }

    // Ações

    @PatchMapping("/{id}/checkin")
    public Reserva checkin(@PathVariable Long id) {
        return service.checkin(id);
    }

    @PatchMapping("/{id}/checkout")
    public Reserva checkout(@PathVariable Long id) {
        return service.checkout(id);
    }

    @PatchMapping("/{id}/cancelar")
    public Reserva cancelar(@PathVariable Long id) {
        return service.cancelar(id);
    }
}