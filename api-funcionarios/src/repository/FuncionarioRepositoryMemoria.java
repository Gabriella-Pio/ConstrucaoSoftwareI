package repository;

import entity.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class FuncionarioRepositoryMemoria implements FuncionarioRepository {
  
  private List<Funcionario> funcionarios = new ArrayList<Funcionario>();

  @Override
  public void salvar(Funcionario f) {
    if (f == null) return;
    if (this.existeFuncionarioComEsseCpf(f.getCpf())) return;

    this.funcionarios.add(f);
  }

  @Override
  public List<Funcionario> listar() {
    return this.funcionarios;
  }

  @Override
  public Funcionario buscarPorId(Integer id) {
    return this.funcionarios
      .stream()
      .filter(f -> f.getId().equals(id))
      .findFirst()
      .orElse(null);
  }

  @Override
  public void atualizar(Funcionario f) {
    if(f == null) return;
    Funcionario funcionarioExistente = buscarPorId(f.getId());
    if(funcionarioExistente == null) return;

    funcionarioExistente.setNome(f.getNome());
    funcionarioExistente.setCpf(f.getCpf());
    funcionarioExistente.setTelefone(f.getTelefone());
    funcionarioExistente.setEmail(f.getEmail());
    funcionarioExistente.setSalario(f.getSalario());
    funcionarioExistente.setCargo(f.getCargo());
  }

  @Override
  public void deletar(Integer id) {
    funcionarios.removeIf(f -> f.getId().equals(id));
  }

  private boolean existeFuncionarioComEsseCpf(String cpf) {
    return this.funcionarios
      .stream()
      .anyMatch(f -> f.getCpf().equals(cpf));
  }
}