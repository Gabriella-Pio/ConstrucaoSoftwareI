package com.gabriella.biblioteca.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_AVALIACAO")
public class Avaliacao {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @ManyToOne
  @JoinColumn(name = "livro_id", nullable = false)
  private Livro livro;

  @Column(nullable = false)
  private Integer nota;

  @Column(length = 1000)
  private String comentario;

  @Column(nullable = false)
  private LocalDateTime dataAvaliacao;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

  public Integer getNota() {
    return nota;
  }

  public void setNota(Integer nota) {
    this.nota = nota;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }

  public LocalDateTime getDataAvaliacao() {
    return dataAvaliacao;
  }

  public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
    this.dataAvaliacao = dataAvaliacao;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
    result = prime * result + ((livro == null) ? 0 : livro.hashCode());
    result = prime * result + ((nota == null) ? 0 : nota.hashCode());
    result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
    result = prime * result + ((dataAvaliacao == null) ? 0 : dataAvaliacao.hashCode());
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
    Avaliacao other = (Avaliacao) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (usuario == null) {
      if (other.usuario != null)
        return false;
    } else if (!usuario.equals(other.usuario))
      return false;
    if (livro == null) {
      if (other.livro != null)
        return false;
    } else if (!livro.equals(other.livro))
      return false;
    if (nota == null) {
      if (other.nota != null)
        return false;
    } else if (!nota.equals(other.nota))
      return false;
    if (comentario == null) {
      if (other.comentario != null)
        return false;
    } else if (!comentario.equals(other.comentario))
      return false;
    if (dataAvaliacao == null) {
      if (other.dataAvaliacao != null)
        return false;
    } else if (!dataAvaliacao.equals(other.dataAvaliacao))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Avaliacao [id=" + id + ", usuario=" + usuario + ", livro=" + livro + ", nota=" + nota + ", comentario="
        + comentario + ", dataAvaliacao=" + dataAvaliacao + "]";
  }
}
