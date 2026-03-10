package br.com.gabriella.tarefa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_TAREFA")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "titulo", length = 100, nullable = false)
    private String titulo;

    @Size(max = 500)
    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "data_hora_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @Future
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_conclusao_prevista")
    private LocalDateTime dataConclusao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "prioridade")
    private Prioridade prioridade;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "tags")
    private String tags;

    // Contrutor Vazio
    public Tarefa() {

    }

    // Contrutor Cheio
    public Tarefa(Long id, String titulo, String descricao, LocalDateTime dataCriacao, LocalDate dataVencimento,
            LocalDateTime dataConclusao, Prioridade prioridade, Status status, String tags) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.dataVencimento = dataVencimento;
        this.dataConclusao = dataConclusao;
        this.prioridade = prioridade;
        this.status = status;
        this.tags = tags;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Tarefa tarefa = (Tarefa) o;
        return Objects.equals(id, tarefa.id) && Objects.equals(titulo, tarefa.titulo)
                && Objects.equals(descricao, tarefa.descricao) && Objects.equals(dataCriacao, tarefa.dataCriacao)
                && Objects.equals(dataVencimento, tarefa.dataVencimento)
                && Objects.equals(dataConclusao, tarefa.dataConclusao) && prioridade == tarefa.prioridade
                && status == tarefa.status && Objects.equals(tags, tarefa.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, descricao, dataCriacao, dataVencimento, dataConclusao, prioridade, status,
                tags);
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataVencimento=" + dataVencimento +
                ", dataConclusao=" + dataConclusao +
                ", prioridade=" + prioridade +
                ", status=" + status +
                ", tags='" + tags + '\'' +
                '}';
    }
}
