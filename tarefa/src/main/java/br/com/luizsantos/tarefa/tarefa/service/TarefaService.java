package br.com.luizsantos.tarefa.tarefa.service;

import br.com.luizsantos.tarefa.tarefa.entity.Tarefa;
import br.com.luizsantos.tarefa.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> findAll(){
        return repository.findAll();
    }

    public Tarefa save(Tarefa tarefa){
        return repository.save(tarefa);
    }

    public List<Tarefa> tarefasNaoConcluidas(){
        return repository.findByDataConclusaoIsNull();
    }
}
