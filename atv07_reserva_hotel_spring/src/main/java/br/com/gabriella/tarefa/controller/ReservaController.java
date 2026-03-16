package br.com.gabriella.tarefa.controller;

import br.com.gabriella.tarefa.entity.Reserva;
import br.com.gabriella.tarefa.entity.Status;
import br.com.gabriella.tarefa.entity.TipoQuarto;
import br.com.gabriella.tarefa.service.ReservaService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public List<Reserva> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Reserva> salvar(@RequestBody @Valid Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(reserva));
    }

    @GetMapping("/{id}")
    public Reserva findById(@PathVariable Long id) {
        return service.buscar(id);
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
        return service.findByStatus(status);
    }

    @GetMapping("/tipo_quarto/{tipoQuarto}")
    public List<Reserva> findByTipoQuarto(@PathVariable TipoQuarto tipoQuarto) {
        return service.findByTipoQuarto(tipoQuarto);
    }

    @GetMapping("/hoje")
    public List<Reserva> reservasHoje() {
        return service.reservasHoje();
    }

    @GetMapping("/proximas")
    public List<Reserva> reservasProximas(@RequestParam(defaultValue = "7") int dias) {
        return service.reservasProximas(dias);
    }

    @GetMapping("/buscar")
    public List<Reserva> buscar(@RequestParam String termo) {
        return service.buscarPorTermo(termo);
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