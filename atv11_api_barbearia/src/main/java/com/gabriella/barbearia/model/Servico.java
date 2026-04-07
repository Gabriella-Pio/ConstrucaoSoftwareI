package com.gabriella.barbearia.model;

import jakarta.persistence.*;
import com.gabriella.barbearia.model.enums.DiaSemana;

import java.util.Set;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gabriella.barbearia.model.Barbeiro;

@Entity
@Table(name = "TB_SERVICO")
public class Servico {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String nome;

  @Column(nullable = false)
  private Double preco;

  @Column(nullable = false)
  private Integer duracaoMinutos;

  // Relacionamento 1:N com ItemPedido
  // @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval =
  // true)
  // @JsonManagedReference
  // private Set<ItemPedido> itens = new HashSet<>();

  public Servico() {
  }

  public Servico(String nome, Double preco, Integer duracaoMinutos) {
    this.nome = nome;
    this.preco = preco;
    this.duracaoMinutos = duracaoMinutos;
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

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public Integer getDuracaoMinutos() {
    return duracaoMinutos;
  }

  public void setDuracaoMinutos(Integer duracaoMinutos) {
    this.duracaoMinutos = duracaoMinutos;
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
    Servico other = (Servico) obj;
    return id != null && id.equals(other.id);
  }

  @Override
  public String toString() {
    return "Servico [id=" + id + ", nome=" + nome + ", preco=" + preco + ", duracaoMinutos=" + duracaoMinutos + "]";
  }
}
