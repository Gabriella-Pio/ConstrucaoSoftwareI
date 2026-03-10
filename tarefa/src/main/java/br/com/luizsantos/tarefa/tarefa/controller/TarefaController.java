package br.com.luizsantos.tarefa.tarefa.controller;

import br.com.luizsantos.tarefa.tarefa.entity.Tarefa;
import br.com.luizsantos.tarefa.tarefa.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    @Autowired
    private TarefaService servcice;

    @GetMapping
    public List<Tarefa> findAll(){
        return servcice.findAll();
    }

    @GetMapping("/nao-concluidas")
    public List<Tarefa> tarefasNaoConcluidas(){
        return servcice.tarefasNaoConcluidas();
    }

    @PostMapping
    public Tarefa save(@RequestBody Tarefa tarefa){
        return servcice.save(tarefa);
    }
}
