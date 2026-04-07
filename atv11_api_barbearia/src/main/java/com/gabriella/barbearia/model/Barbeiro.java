package com.gabriella.barbearia.model;

import jakarta.persistence.*;
import com.gabriella.barbearia.model.enums.Especialidade;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "TB_BARBEIRO")
public class Barbeiro {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Especialidade especialidade;

  // Relacionamento 1:N com HorarioTrabalho
  @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private Set<HorarioTrabalho> horarioTrabalho = new HashSet<>();

  // Relacionamento 1:N com Agendamento
  @OneToMany(mappedBy = "barbeiro", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonManagedReference
  private Set<Agendamento> agendamentos = new HashSet<>();

  // Relacionamento N:1 com Cliente
  // @ManyToOne
  // @JoinColumn(name = "cliente_id", nullable = false)
  // @JsonBackReference
  // private Cliente cliente;

  // // Relacionamento 1:1 com Endereco
  // @OneToOne(mappedBy = "pedido")
  // @JsonManagedReference
  // private Endereco endereco;

  public Barbeiro() {
  }

  public Barbeiro(UUID id, String nome, Especialidade especialidade) {
    this.id = id;
    this.nome = nome;
    this.especialidade = especialidade;
  }

  public Barbeiro(UUID id, String nome, Especialidade especialidade, Set<HorarioTrabalho> horarioTrabalhos) {
    this.id = id;
    this.nome = nome;
    this.especialidade = especialidade;
    this.horarioTrabalho = horarioTrabalhos;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Especialidade getEspecialidade() {
    return especialidade;
  }

  public void setEspecialidade(Especialidade especialidade) {
    this.especialidade = especialidade;
  }

  public Set<HorarioTrabalho> getHorariosTrabalho() {
    return horarioTrabalho;
  }

  public void setHorarioTrabalho(Set<HorarioTrabalho> horarioTrabalho) {
    this.horarioTrabalho = horarioTrabalho;
  }

  public Set<Agendamento> getAgendamentos() {
    return agendamentos;
  }

  public void setAgendamentos(Set<Agendamento> agendamentos) {
    this.agendamentos = agendamentos;
  }

  @Override
  public int hashCode() {
    return ((id == null) ? 0 : id.hashCode());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    Barbeiro other = (Barbeiro) obj;
    return id != null && id.equals(other.id);
  }

  @Override
  public String toString() {
    return "Barbeiro [id=" + id + ", nome=" + nome + ", especialidade=" + especialidade + "]";
  }
}
