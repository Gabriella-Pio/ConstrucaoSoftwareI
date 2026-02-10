package service;

import java.util.List;

import entity.Funcionario;
import repository.FuncionarioRepository;
import repository.FuncionarioRepositoryMemoria;

public class FuncionarioService {

  FuncionarioRepository funcionarioRepository;

  public FuncionarioService(FuncionarioRepository funcionarioRepository) {
    this.funcionarioRepository = funcionarioRepository;
  }

  private Integer id = 0;

  public boolean salvarFuncionario(String nome, String cpf, String telefone, String email, Double salario, String cargo) {
    try {
      validarNegocio(cpf, telefone, email, salario, cargo);
      Funcionario funcionario = new Funcionario(id++, nome, cpf, telefone, email, salario, cargo);
      this.funcionarioRepository.salvar(funcionario);
      return true;
    } catch (IllegalAccessException e) {
      System.out.println("Erro ao salvar funcionário: " + e.getMessage());
      return false;
    }
  }

  public List<Funcionario> listarFuncionarios() {
    return funcionarioRepository.listar();
  }

  public Funcionario buscarFuncionarioPorId(Integer id) {
    return funcionarioRepository.buscarPorId(id);
  }

  public void deletarFuncionario(Integer id) {
    funcionarioRepository.deletar(id);
  }

  public boolean atualizarFuncionario(Integer id, String nome, String cpf, String telefone, String email, Double salario,
      String cargo) {
    try {
      validarNegocio(cpf, telefone, email, salario, cargo);
      Funcionario funcionario = new Funcionario(id, nome, cpf, telefone, email, salario, cargo);
      funcionarioRepository.atualizar(funcionario);
      return true;
    } catch (IllegalAccessException e) {
      System.out.println("Erro ao atualizar funcionário: " + e.getMessage());
      return false;
    }
  }

  private void validarNegocio(String cpf, String telefone, String email, Double salario, String cargo)
      throws IllegalAccessException {

    if (cpf == null || cpf.isEmpty()) {
      throw new IllegalAccessException("O CPF do funcionário é obrigatório.");
    }
    if (telefone == null || telefone.isEmpty()) {
      throw new IllegalAccessException("O telefone do funcionário é obrigatório.");
    }
    if (email == null || email.isEmpty() || !email.contains("@")) {
      throw new IllegalAccessException("O email do funcionário é obrigatório.");
    }
    if (salario == null || salario < 1600) {
      throw new IllegalAccessException("O salário do funcionário deve ser maior que um salário mínimo.");
    }
    if (cargo == null || cargo.isEmpty()) {
      throw new IllegalAccessException("O cargo do funcionário é obrigatório.");
    }
  }

}
