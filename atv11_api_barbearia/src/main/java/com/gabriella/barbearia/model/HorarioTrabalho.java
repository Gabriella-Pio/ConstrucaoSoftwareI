package com.gabriella.barbearia.model;

import jakarta.persistence.*;
import com.gabriella.barbearia.model.enums.DiaSemana;

import java.util.Set;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TB_HORARIO_TRABALHO")
public class HorarioTrabalho {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private DiaSemana diaSemana;

  @Column(nullable = false)
  private LocalTime horaInicio;

  @Column(nullable = false)
  private LocalTime horaFim;

  // Relacionamento N:1 com Barbeiro
  @ManyToOne
  @JoinColumn(name = "barbeiro_id", nullable = false)
  @JsonBackReference
  private Barbeiro barbeiro;

  // Relacionamento 1:N com ItemPedido
  // @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval =
  // true)
  // @JsonManagedReference
  // private Set<ItemPedido> itens = new HashSet<>();

  public HorarioTrabalho() {
  }

  public HorarioTrabalho(DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFim) {
    this.diaSemana = diaSemana;
    this.horaInicio = horaInicio;
    this.horaFim = horaFim;
  }

  public HorarioTrabalho(UUID id, DiaSemana diaSemana, LocalTime horaInicio, LocalTime horaFim, Barbeiro barbeiro) {
    this.id = id;
    this.diaSemana = diaSemana;
    this.horaInicio = horaInicio;
    this.horaFim = horaFim;
    this.barbeiro = barbeiro;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public DiaSemana getDiaSemana() {
    return diaSemana;
  }

  public void setDiaSemana(DiaSemana diaSemana) {
    this.diaSemana = diaSemana;
  }

  public LocalTime getHoraInicio() {
    return horaInicio;
  }

  public void setHoraInicio(LocalTime horaInicio) {
    this.horaInicio = horaInicio;
  }

  public LocalTime getHoraFim() {
    return horaFim;
  }

  public void setHoraFim(LocalTime horaFim) {
    this.horaFim = horaFim;
  }

  public Barbeiro getBarbeiro() {
    return barbeiro;
  }

  public void setBarbeiro(Barbeiro barbeiro) {
    this.barbeiro = barbeiro;
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
    HorarioTrabalho other = (HorarioTrabalho) obj;
    return id != null && id.equals(other.id);
  }

  @Override
  public String toString() {
    return "HorarioTrabalho [id=" + id + ", diaSemana=" + diaSemana + ", horaInicio=" + horaInicio + ", horaFim="
        + horaFim + ", barbeiro=" + barbeiro + "]";
  }
}
