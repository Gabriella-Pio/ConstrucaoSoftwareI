package com.gabriella.biblioteca.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "TB_AUTOR")
public class Autor {

  // ID
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  // Nome
  @Column(nullable = false)
  private String nome;

  // Email
  @Column(unique = true, nullable = false)
  @Email
  private String email;

  private String biografia;

  @ManyToMany(mappedBy = "autores")
  private Set<Livro> livros = new HashSet<>();

  public Autor() {
  }

  public Autor(UUID id, String nome, String email, String biografia) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.biografia = biografia;
  }

  public void addLivro(Livro livro) {
    livros.add(livro);
    livro.addAutor(this);
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getBiografia() {
    return biografia;
  }

  public void setBiografia(String biografia) {
    this.biografia = biografia;
  }

  public Set<Livro> getLivros() {
    return livros;
  }

  public void removerLivro(Livro livro) {
    livros.remove(livro);
    livro.removerAutor(this);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((biografia == null) ? 0 : biografia.hashCode());
    result = prime * result + ((livros == null) ? 0 : livros.hashCode());
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
    Autor other = (Autor) obj;
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
    if (biografia == null) {
      if (other.biografia != null)
        return false;
    } else if (!biografia.equals(other.biografia))
      return false;
    if (livros == null) {
      if (other.livros != null)
        return false;
    } else if (!livros.equals(other.livros))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Autor [id=" + id + ", nome=" + nome + ", email=" + email + ", biografia=" + biografia + ", livros=" + livros
        + "]";
  }
}
