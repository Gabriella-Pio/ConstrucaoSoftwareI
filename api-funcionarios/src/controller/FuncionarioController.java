package controller;

import service.FuncionarioService;

public class FuncionarioController {
  
  private FuncionarioService funcionarioService;

  public FuncionarioController(FuncionarioService funcionarioService) {
    this.funcionarioService = funcionarioService;
  }

  public boolean salvarFuncionario(String nome, String cpf, String telefone, String email, Double salario, String cargo) {
    return funcionarioService.salvarFuncionario(nome, cpf, telefone, email, salario, cargo);
  }

  public void listarFuncionarios() {
    funcionarioService.listarFuncionarios().forEach(f -> {
      System.out.println("-----------------------------");
      System.out.println("ID: " + f.getId());
      System.out.println("Nome: " + f.getNome());
      System.out.println("CPF: " + f.getCpf());
      System.out.println("Telefone: " + f.getTelefone());
      System.out.println("Email: " + f.getEmail());
      System.out.println("Salário: " + f.getSalario());
      System.out.println("Cargo: " + f.getCargo());
      System.out.println("-----------------------------");
    });
  }

  public void buscarFuncionarioPorId(Integer id) {
    var funcionario = funcionarioService.buscarFuncionarioPorId(id);
    if (funcionario != null) {
      System.out.println("ID: " + funcionario.getId());
      System.out.println("Nome: " + funcionario.getNome());
      System.out.println("CPF: " + funcionario.getCpf());
      System.out.println("Telefone: " + funcionario.getTelefone());
      System.out.println("Email: " + funcionario.getEmail());
      System.out.println("Salário: " + funcionario.getSalario());
      System.out.println("Cargo: " + funcionario.getCargo());
    } else {
      System.out.println("Funcionário com ID " + id + " não encontrado.");
    }
  }

  public void deletarFuncionario(Integer id) {
    funcionarioService.deletarFuncionario(id);
  }

  public boolean atualizarFuncionario(Integer id, String nome, String cpf, String telefone, String email, Double salario, String cargo) {
    return funcionarioService.atualizarFuncionario(id, nome, cpf, telefone, email, salario, cargo);
  }
}
