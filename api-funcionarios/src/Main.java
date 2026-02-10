import repository.FuncionarioRepository;
import repository.FuncionarioRepositoryMemoria;
import service.FuncionarioService;
import java.util.Scanner;
import controller.FuncionarioController;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    FuncionarioRepository funcionarioRepository = new FuncionarioRepositoryMemoria();
    FuncionarioService funcionarioService = new FuncionarioService(funcionarioRepository);
    FuncionarioController funcionarioController = new FuncionarioController(funcionarioService);

    while (true) {
      System.out.println("\n=== MENU FUNCIONÁRIOS ===");
      System.out.println("1. Salvar funcionário");
      System.out.println("2. Listar funcionários");
      System.out.println("3. Buscar funcionário por ID");
      System.out.println("4. Deletar funcionário");
      System.out.println("5. Atualizar funcionário");
      System.out.println("0. Sair");
      System.out.print("Escolha uma opção: ");

      // Usar nextLine e converter para int evita problemas com o buffer do teclado
      String entrada = scanner.nextLine();
      int opcao;
      try {
        opcao = Integer.parseInt(entrada);
      } catch (NumberFormatException e) {
        System.out.println("Por favor, digite um número válido.");
        continue;
      }

      switch (opcao) {
        case 1:
          cadastrarFuncionario(scanner, funcionarioController);
          break;
        case 2:
          funcionarioController.listarFuncionarios();
          break;
        case 3:
          buscarFuncionarioPorId(scanner, funcionarioController);
          break;
        case 4:
          deletarFuncionario(scanner, funcionarioController);
          break;
        case 5:
          atualizarFuncionario(scanner, funcionarioController);
          break;
        case 0:
          System.out.println("Saindo...");
          scanner.close();
          return;
        default:
          System.out.println("Opção inválida!");
      }
    }
  } // Fim do main

  private static void cadastrarFuncionario(Scanner scanner, FuncionarioController controller) {
    System.out.print("Nome: ");
    String nome = scanner.nextLine();
    System.out.print("CPF: ");
    String cpf = scanner.nextLine();
    System.out.print("Telefone: ");
    String telefone = scanner.nextLine();
    System.out.print("Email: ");
    String email = scanner.nextLine();
    System.out.print("Salário: ");
    Double salario = Double.parseDouble(scanner.nextLine());
    System.out.print("Cargo: ");
    String cargo = scanner.nextLine();

    if (controller.salvarFuncionario(nome, cpf, telefone, email, salario, cargo)) {
      System.out.println("Funcionário salvo com sucesso!");
    }
  }

  private static void buscarFuncionarioPorId(Scanner scanner, FuncionarioController controller) {
    System.out.print("Digite o ID: ");
    Integer id = Integer.parseInt(scanner.nextLine());
    controller.buscarFuncionarioPorId(id);
  }

  private static void deletarFuncionario(Scanner scanner, FuncionarioController controller) {
    System.out.print("Digite o ID do funcionário a deletar: ");
    Integer id = Integer.parseInt(scanner.nextLine());
    controller.deletarFuncionario(id);
    System.out.println("Funcionário deletado com sucesso!");
  }

  private static void atualizarFuncionario(Scanner scanner, FuncionarioController controller) {
    System.out.print("Digite o ID do funcionário a atualizar: ");
    Integer id = Integer.parseInt(scanner.nextLine());
    System.out.print("Novo nome: ");
    String nome = scanner.nextLine();
    System.out.print("Novo CPF: ");
    String cpf = scanner.nextLine();
    System.out.print("Novo telefone: ");
    String telefone = scanner.nextLine();
    System.out.print("Novo email: ");
    String email = scanner.nextLine();
    System.out.print("Novo salário: ");
    Double salario = Double.parseDouble(scanner.nextLine());
    System.out.print("Novo cargo: ");
    String cargo = scanner.nextLine();

    if (controller.atualizarFuncionario(id, nome, cpf, telefone, email, salario, cargo)) {
      System.out.println("Funcionário atualizado com sucesso!");
    }
  }
}