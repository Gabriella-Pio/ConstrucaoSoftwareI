package com.gabriella.pedidos.entity;

import java.lang.annotation.Inherited;
import java.time.LocalDate;
import java.util.UUID;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
@Entity
@Table(name = "TB_PEDIDOS")
public class Pedidos {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String descricao;

  @Column(nullable = false)
  private Double preco;

  @Column(nullable = false)
  private LocalDate dataPedido;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cliente_id", nullable = false)
  @JsonBackReference
  private Cliente cliente;

  public Pedidos() {
  }

  public Pedidos(UUID id, String descricao, Double preco, LocalDate dataPedido, Cliente cliente) {
    this.id = id;
    this.descricao = descricao;
    this.preco = preco;
    this.dataPedido = dataPedido;
    this.cliente = cliente;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public Double getPreco() {
    return preco;
  }

  public void setPreco(Double preco) {
    this.preco = preco;
  }

  public LocalDate getDataPedido() {
    return dataPedido;
  }

  public void setDataPedido(LocalDate dataPedido) {
    this.dataPedido = dataPedido;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
    result = prime * result + ((preco == null) ? 0 : preco.hashCode());
    result = prime * result + ((dataPedido == null) ? 0 : dataPedido.hashCode());
    result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Pedidos other = (Pedidos) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (descricao == null) {
      if (other.descricao != null)
        return false;
    } else if (!descricao.equals(other.descricao))
      return false;
    if (preco == null) {
      if (other.preco != null)
        return false;
    } else if (!preco.equals(other.preco))
      return false;
    if (dataPedido == null) {
      if (other.dataPedido != null)
        return false;
    } else if (!dataPedido.equals(other.dataPedido))
      return false;
    if (cliente == null) {
      if (other.cliente != null)
        return false;
    } else if (!cliente.equals(other.cliente))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Pedidos [id=" + id + ", descricao=" + descricao + ", preco=" + preco + ", dataPedido=" + dataPedido
        + ", cliente=" + cliente + "]";
  }

}
