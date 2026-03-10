package br.com.luizsantos.tarefa.tarefa.repository;

import br.com.luizsantos.tarefa.tarefa.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {

    //QUERY JPA SQL - JPQL
    @Query("Select t from Tarefa t")
    List<Tarefa> retornarTodasTarefas();

    @Query("Select t from Tarefa t where t.dataConcluido is null")
    List<Tarefa> naoConcluidas();

    //QUERY NATIVE SQL
    @Query(nativeQuery = true, value = "SELECT * FROM TB_TAREFA WHERE DATA_CONCLUSAO IS NULL")
    List<Tarefa> naoConcluidasNative();

    //QUERY ORIENTADA A METODO
    List<Tarefa> findByDataConclusaoIsNull();
}
