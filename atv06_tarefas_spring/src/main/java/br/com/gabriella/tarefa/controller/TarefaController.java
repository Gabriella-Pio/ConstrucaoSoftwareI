package br.com.gabriella.tarefa.controller;

import br.com.gabriella.tarefa.entity.Tarefa;
import br.com.gabriella.tarefa.service.TarefaService;
import br.com.gabriella.tarefa.entity.Prioridade;
import br.com.gabriella.tarefa.entity.Status;
import br.com.gabriella.tarefa.repository.TarefaRepository;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService service;

    @GetMapping
    public List<Tarefa> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Tarefa> salvar(@RequestBody @Valid Tarefa tarefa) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(tarefa));
    }

    @GetMapping("/{id}")
    public Tarefa findById(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public Tarefa update(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return service.atualizar(id, tarefa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deletar(id);
    }

    // Filtros e Busca
    @GetMapping("/status/{status}")
    public List<Tarefa> findByStatus(@PathVariable Status status) {
        return service.findByStatus(status);
    }

    @GetMapping("/prioridade/{prioridade}")
    public List<Tarefa> findByPrioridade(@PathVariable Prioridade prioridade) {
        return service.findByPrioridade(prioridade);
    }

    @GetMapping("/vencidas")
    public List<Tarefa> tarefasVencidas() {
        return service.findVencidas();
    }

    @GetMapping("/proximas")
    public List<Tarefa> tarefasProximas(@RequestParam(defaultValue = "7") int dias) {
        return service.findProximas(dias);
    }

    @GetMapping("/buscar")
    public List<Tarefa> buscarPorTermo(@RequestParam String termo) {
        return service.buscarPorTermo(termo);
    }

    @GetMapping("/tag/{tag}")
    public List<Tarefa> findByTag(@PathVariable String tag) {
        return service.findByTag(tag);
    }

    // Endpoints de ação
    @PatchMapping("/{id}/iniciar")
    public Tarefa iniciarTarefa(@PathVariable Long id) {
        return service.iniciar(id);
    }

    @PatchMapping("/{id}/concluir")
    public Tarefa concluirTarefa(@PathVariable Long id) {
        return service.concluir(id);
    }

    @PatchMapping("/{id}/cancelar")
    public Tarefa cancelarTarefa(@PathVariable Long id) {
        return service.cancelar(id);
    }

    @PatchMapping("/{id}/prioridade/{prioridade}")
    public Tarefa atualizarPrioridade(@PathVariable Long id, @PathVariable Prioridade prioridade) {
        return service.alterarPrioridade(id, prioridade);
    }

}
