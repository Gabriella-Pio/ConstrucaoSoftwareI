package main.java.com.gabriella.pedidos.entity;

import java.lang.annotation.Inherited;

import javax.annotation.processing.Generated;

@Entity
@Table("TB_CLIENTE")
public class Cliente {

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

  // Telefone
  @Column(lenght = 20)
  private String telefone;

  // Cpf
  @Column(unique = true, nullable = false, lenght = 11)
  private String cpf;

  // Endereço
  @Column
  private String endereco;

  public Cliente() {
  }

  public Cliente(UUID id, String nome, String email, String telefone, String cpf, String endereco) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.telefone = telefone;
    this.cpf = cpf;
    this.endereco = endereco;
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

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Pedido> pedidos;

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((nome == null) ? 0 : nome.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
    result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
    result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
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
    Cliente other = (Cliente) obj;
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
    if (telefone == null) {
      if (other.telefone != null)
        return false;
    } else if (!telefone.equals(other.telefone))
      return false;
    if (cpf == null) {
      if (other.cpf != null)
        return false;
    } else if (!cpf.equals(other.cpf))
      return false;
    if (endereco == null) {
      if (other.endereco != null)
        return false;
    } else if (!endereco.equals(other.endereco))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Cliente [id=" + id + ", nome=" + nome + ", email=" + email + ", telefone=" + telefone + ", cpf=" + cpf
        + ", endereco=" + endereco + "]";
  }

}
