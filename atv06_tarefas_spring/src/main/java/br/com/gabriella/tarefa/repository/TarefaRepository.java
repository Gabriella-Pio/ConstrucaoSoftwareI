package br.com.gabriella.tarefa.repository;

import br.com.gabriella.tarefa.entity.Tarefa;
import br.com.gabriella.tarefa.entity.Prioridade;
import br.com.gabriella.tarefa.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    List<Tarefa> findByStatus(Status status);

    List<Tarefa> findByPrioridade(Prioridade prioridade);

    List<Tarefa> findByDataVencimentoBefore(LocalDate data);

    List<Tarefa> findByDataVencimentoBetween(LocalDate hoje, LocalDate dataFutura);

    List<Tarefa> findByTituloContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String termo1, String termo2);

    List<Tarefa> findByTagsContainingIgnoreCase(String tag);

    // QUERY JPA SQL - JPQL
    @Query("Select t from Tarefa t")
    List<Tarefa> retornarTodasTarefas();

    @Query("Select t from Tarefa t where t.dataConcluido is null")
    List<Tarefa> naoConcluidas();

    // QUERY NATIVE SQL
    @Query(nativeQuery = true, value = "SELECT * FROM TB_TAREFA WHERE DATA_CONCLUSAO IS NULL")
    List<Tarefa> naoConcluidasNative();

    // QUERY ORIENTADA A METODO
    List<Tarefa> findByDataConclusaoIsNull();

    @Query(value = "SELECT * FROM TB_TAREFA WHERE STATUS = :status", nativeQuery = true)
    List<Tarefa> procurarPorStatus(Status status);

    @Query(value = "SELECT * FROM TB_TAREFA WHERE PRIORIDADE = :prioridade", nativeQuery = true)
    List<Tarefa> procurarPorPrioridade(Prioridade prioridade);

    @Query(value = "SELECT * FROM TB_TAREFA WHERE DATA_VENCIMENTO < CURRENT_DATE AND DATA_CONCLUSAO IS NULL", nativeQuery = true)
    List<Tarefa> procurarPorVencida();

}
