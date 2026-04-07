package com.gabriella.barbearia.model;

import jakarta.persistence.*;
import com.gabriella.barbearia.model.Servico;
import com.gabriella.barbearia.model.enums.Status;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "TB_AGENDAMENTO")
public class Agendamento {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private LocalDateTime dataHora;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status;

  // Relacionamento N:1 com Cliente
  @ManyToOne
  @JoinColumn(name = "cliente_id", nullable = false)
  @JsonBackReference
  private Cliente cliente;

  // Relacionamento N:1 com Barbeiro
  @ManyToOne
  @JoinColumn(name = "barbeiro_id", nullable = false)
  @JsonBackReference
  private Barbeiro barbeiro;

  // Relacionamento N:1 com Barbeiro
  @ManyToOne
  @JoinColumn(name = "servico_id", nullable = false)
  @JsonBackReference
  private Servico servico;

  public Agendamento() {
  }

  public Agendamento(UUID id, LocalDateTime dataHora, Status status) {
    this.id = id;
    this.dataHora = dataHora;
    this.status = status;
  }

  public Agendamento(UUID id, LocalDateTime dataHora, Status status, Cliente cliente, Barbeiro barbeiro,
      Servico servico) {
    this.id = id;
    this.dataHora = dataHora;
    this.status = status;
    this.cliente = cliente;
    this.barbeiro = barbeiro;
    this.servico = servico;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public LocalDateTime getDataHora() {
    return dataHora;
  }

  public void setDataHora(LocalDateTime dataHora) {
    this.dataHora = dataHora;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Barbeiro getBarbeiro() {
    return barbeiro;
  }

  public void setBarbeiro(Barbeiro barbeiro) {
    this.barbeiro = barbeiro;
  }

  public Servico getServico() {
    return servico;
  }

  public void setServico(Servico servico) {
    this.servico = servico;
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
    Agendamento other = (Agendamento) obj;
    return id != null && id.equals(other.id);
  }

  @Override
  public String toString() {
    return "Agendamento [id=" + id + ", dataHora=" + dataHora + ", status=" + status + ", cliente=" + cliente
        + ", barbeiro=" + barbeiro + ", servico=" + servico + "]";
  }
}
