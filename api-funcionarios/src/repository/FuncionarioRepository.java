package repository;

import java.util.List;
import entity.Funcionario;

public interface FuncionarioRepository {

  void salvar(Funcionario f);

  List<Funcionario> listar();
  
  Funcionario buscarPorId(Integer id);
  
  void atualizar(Funcionario f);
  
  void deletar(Integer id);
}