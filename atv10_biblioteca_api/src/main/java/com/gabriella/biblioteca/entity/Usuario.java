package com.gabriella.biblioteca.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Table(name = "TB_USUARIO")
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String nome;

  @Column
  private String email;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Avaliacao> avaliacoes = new HashSet<>();

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Avaliacao> getAvaliacoes() {
    return avaliacoes;
  }

  public void setAvaliacoes(Set<Avaliacao> avaliacoes) {
    this.avaliacoes = avaliacoes;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((avaliacoes == null) ? 0 : avaliacoes.hashCode());
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
    Usuario other = (Usuario) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (nome == null) {
      if (other.nome != null)
        return false;
    } else if (!nome.equals(other.nome))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (avaliacoes == null) {
      if (other.avaliacoes != null)
        return false;
    } else if (!avaliacoes.equals(other.avaliacoes))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", avaliacoes=" + avaliacoes + "]";
  }

}
