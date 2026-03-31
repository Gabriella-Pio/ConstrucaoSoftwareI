package com.gabriella.biblioteca.entity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_LIVRO")
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false)
  private String titulo;

  @Column(nullable = false)
  private Integer anoPublicacao;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "TB_LIVRO_AUTOR", joinColumns = @JoinColumn(name = "livro_id"), inverseJoinColumns = @JoinColumn(name = "autor_id"))
  private Set<Autor> autores = new HashSet<>();

  @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Avaliacao> avaliacoes = new HashSet<>();

  public Livro() {
  }

  public Livro(String titulo, Integer anoPublicacao) {
    this.titulo = titulo;
    this.anoPublicacao = anoPublicacao;
  }

  public Livro(UUID id, String titulo, Integer anoPublicacao) {
    this.id = id;
    this.titulo = titulo;
    this.anoPublicacao = anoPublicacao;
  }

  public void addAutor(Autor autor) {
    autores.add(autor);
    autor.addLivro(this);
  }

  public void removerAutor(Autor autor) {
    autores.remove(autor);
    autor.removerLivro(this);
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public Integer getAnoPublicacao() {
    return anoPublicacao;
  }

  public void setAnoPublicacao(Integer anoPublicacao) {
    this.anoPublicacao = anoPublicacao;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
    result = prime * result + ((anoPublicacao == null) ? 0 : anoPublicacao.hashCode());
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
    Livro other = (Livro) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (titulo == null) {
      if (other.titulo != null)
        return false;
    } else if (!titulo.equals(other.titulo))
      return false;
    if (anoPublicacao == null) {
      if (other.anoPublicacao != null)
        return false;
    } else if (!anoPublicacao.equals(other.anoPublicacao))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Livro [id=" + id + ", titulo=" + titulo + ", anoPublicacao=" + anoPublicacao + "]";
  }

}
