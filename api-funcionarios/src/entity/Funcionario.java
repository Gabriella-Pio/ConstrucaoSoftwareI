package entity;

public class Funcionario {

  private Integer id;
  private String nome;
  private String cpf;
  private String telefone;
  private String email;
  private Double salario;
  private String cargo;

  // Construtor
  public Funcionario(Integer id, String nome, String cpf, String telefone, String email, Double salario, String cargo) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.telefone = telefone;
    this.email = email;
    this.salario = salario;
    this.cargo = cargo;
  }

  // Getters e Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getTelefone() {
    return telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Double getSalario() {
    return salario;
  }

  public void setSalario(Double salario) {
    this.salario = salario;
  }

  public String getCargo() {
    return cargo;
  }

  public void setCargo(String cargo) {
    this.cargo = cargo;
  }

  // equals e hashCode - compara a posição do objeto na memória
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Funcionario that = (Funcionario) o;

    return id == that.id;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  
  // toString - exibe o conteúdo do objeto
  @Override
  public String toString() {
    return "Funcionario{" +
            "id=" + id +
            ", nome='" + nome + '\'' +
            ", cpf='" + cpf + '\'' +
            ", telefone='" + telefone + '\'' +
            ", email='" + email + '\'' +
            ", salario=" + salario +
            ", cargo='" + cargo + '\'' +
            '}';
  }
}