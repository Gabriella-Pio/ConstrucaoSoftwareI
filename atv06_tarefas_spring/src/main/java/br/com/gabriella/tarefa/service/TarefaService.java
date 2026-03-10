package br.com.gabriella.tarefa.service;

import br.com.gabriella.tarefa.entity.Tarefa;
import br.com.gabriella.tarefa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.gabriella.tarefa.entity.Prioridade;
import br.com.gabriella.tarefa.entity.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public Tarefa salvar(Tarefa tarefa) {
        tarefa.setDataCriacao(LocalDateTime.now());
        tarefa.setStatus(Status.PENDENTE);
        tarefa.setDataConclusao(null);
        return repository.save(tarefa);
    }

    public Tarefa atualizar(Long id, Tarefa dadosNovos) {
        Tarefa existente = buscar(id);
        existente.setTitulo(dadosNovos.getTitulo());
        existente.setDescricao(dadosNovos.getDescricao());
        existente.setDataVencimento(dadosNovos.getDataVencimento());
        existente.setPrioridade(dadosNovos.getPrioridade());
        existente.setTags(dadosNovos.getTags());
        return repository.save(existente);
    }

    // Regras de transição
    public Tarefa iniciar(Long id) {
        Tarefa tarefa = buscar(id);

        // se estiver cancelada ou concluída não pode iniciar
        if (tarefa.getStatus() == Status.CANCELADA || tarefa.getStatus() == Status.CONCLUIDA) {
            throw new IllegalStateException("Transição de status inválida");
        }

        tarefa.setStatus(Status.EM_PROGRESSO);
        return repository.save(tarefa);
    }

    public Tarefa cancelar(Long id) {
        Tarefa tarefa = buscar(id);

        // só pode cancelar se estiver pendente ou em progresso
        if (tarefa.getStatus() == Status.CONCLUIDA) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é possível cancelar esta tarefa.");
        }

        tarefa.setStatus(Status.CANCELADA);
        return repository.save(tarefa);
    }

    public void deletar(Long id) {
        Tarefa tarefa = buscar(id);

        // não pode deletar se estiver em progresso
        if (tarefa.getStatus() == Status.EM_PROGRESSO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é possível deletar uma tarefa em progresso.");
        }
        repository.delete(tarefa);
    }

    public Tarefa concluir(Long id) {
        Tarefa tarefa = buscar(id);

        // só pode concluir se estiver em progresso
        if (tarefa.getStatus() != Status.EM_PROGRESSO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Não é possível concluir esta tarefa.");
        }

        tarefa.setStatus(Status.CONCLUIDA);
        tarefa.setDataConclusao(LocalDateTime.now());
        return repository.save(tarefa);
    }

    public Tarefa alterarPrioridade(Long id, Prioridade novaPrioridade) {
    Tarefa tarefa = buscar(id);
    tarefa.setPrioridade(novaPrioridade);
    return repository.save(tarefa);
}

    // métodos de busca para o controller
    public List<Tarefa> findAll() {
        return repository.findAll();
    }

    public List<Tarefa> findByStatus(Status s) {
        return repository.findByStatus(s);
    }

    public List<Tarefa> findByPrioridade(Prioridade p) {
        return repository.findByPrioridade(p);
    }

    public List<Tarefa> findVencidas() {
        return repository.findByDataVencimentoBefore(LocalDate.now());
    }

    public List<Tarefa> findProximas(int dias) {
        LocalDate hoje = LocalDate.now();
        LocalDate dataFutura = hoje.plusDays(dias);
        return repository.findByDataVencimentoBetween(hoje, dataFutura);
    }

    public List<Tarefa> buscarPorTermo(String termo) {
        return repository.findByTituloContainingIgnoreCaseOrDescricaoContainingIgnoreCase(termo, termo);
    }

    public List<Tarefa> findByTag(String tag) {
        return repository.findByTagsContainingIgnoreCase(tag);
    }

    public Tarefa buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    // public List<Tarefa> tarefasNaoConcluidas() {
    // return repository.findByDataConclusaoIsNull();
    // }
}
