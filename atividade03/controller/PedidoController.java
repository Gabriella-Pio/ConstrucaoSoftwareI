package atividade03.controller;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import atividade03.entity.Pedido;
import atividade03.services.PedidoService;

public class PedidoController {
  private final PedidoService service;
  private final Scanner scanner;

  public PedidoController(PedidoService service) {
    this.service = service;
    this.scanner = new Scanner(System.in);
  }

  public void executar() {
    showMenu();
  }

  public void showMenu() {
    int option = -1;

    while (option != 0) {
      System.out.println("\n=== SISTEMA DE GESTÃO DE PEDIDOS ===");
      System.out.println("1. Cadastrar Pedido");
      System.out.println("2. Listar Todos os Pedidos");
      System.out.println("3. Buscar Pedido por ID");
      System.out.println("4. Atualizar Pedido");
      System.out.println("5. Marcar como Pago");
      System.out.println("6. Marcar como Enviado");
      System.out.println("7. Cancelar Pedido");
      System.out.println("8. Remover Pedido");
      System.out.println("0. Sair");
      System.out.println("---------------------------------------");
      System.out.print("Escolha uma opção: ");

      try {
        option = Integer.parseInt(scanner.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Digite um número.");
        continue;
      }

      System.out.println("---------------------------------------");

      switch (option) {
        case 1:
          cadastrarPedido();
          break;
        case 2:
          listarPedidos();
          break;
        case 3:
          buscarPorId();
          break;
        case 4:
          atualizarPedido();
          break;
        case 5:
          marcarPago();
          break;
        case 6:
          marcarEnviado();
          break;
        case 7:
          cancelarPedido();
          break;
        case 8:
          removerPedido();
          break;
        case 0:
          System.out.println("Saindo do sistema...");
          break;
        default:
          System.out.println("Opção inválida. Tente novamente.");
          break;
      }
    }
  }

  // 1. Cadastrar Pedido
  private void cadastrarPedido() {
    System.out.print("Nome do cliente: ");
    String cliente = scanner.nextLine().trim();

    System.out.print("Descrição do pedido: ");
    String descricao = scanner.nextLine().trim();

    System.out.print("Valor do pedido (ex: 49.90): ");
    double valor;
    try {
      valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
    } catch (NumberFormatException e) {
      System.out.println("Valor inválido. Operação cancelada.");
      return;
    }

    try {
      service.cadastrarPedido(cliente, descricao, valor);
      System.out.println("Pedido cadastrado com sucesso!");
    } catch (IllegalArgumentException e) {
      System.out.println("Erro ao cadastrar: " + e.getMessage());
    }
  }

  // 2. Listar Todos os Pedidos
  private void listarPedidos() {
    List<Pedido> pedidos = service.listarPedidos();

    if (pedidos.isEmpty()) {
      System.out.println("Nenhum pedido encontrado.");
      return;
    }

    System.out.printf("%-5s | %-20s | %-30s | %10s | %-10s | %s%n",
        "ID", "Cliente", "Descrição", "Valor (R$)", "Status", "Data de Criação");
    System.out.println("-".repeat(100));

    for (Pedido p : pedidos) {
      System.out.printf("%-5d | %-20s | %-30s | %10.2f | %-10s | %s%n",
          p.getId(),
          p.getCliente(),
          p.getDescricao(),
          p.getValor(),
          p.getStatus(),
          p.getDataCriacao());
    }
  }

  // 3. Buscar Pedido por ID
  private void buscarPorId() {
    System.out.print("Digite o ID do pedido: ");
    int id;
    try {
      id = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("ID inválido.");
      return;
    }

    Optional<Pedido> resultado = service.buscarPedidoPorId(id);

    if (resultado.isPresent()) {
      Pedido p = resultado.get();
      System.out.println("Pedido encontrado:");
      System.out.printf("  ID: %d%n", p.getId());
      System.out.printf("  Cliente: %s%n", p.getCliente());
      System.out.printf("  Descrição: %s%n", p.getDescricao());
      System.out.printf("  Valor: R$ %.2f%n", p.getValor());
      System.out.printf("  Status: %s%n", p.getStatus());
      System.out.printf("  Data de Criação: %s%n", p.getDataCriacao());
    } else {
      System.out.println("Pedido com ID " + id + " não encontrado.");
    }
  }

  // 4. Atualizar Pedido
  private void atualizarPedido() {
    System.out.print("Digite o ID do pedido a atualizar: ");
    int id;
    try {
      id = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("ID inválido.");
      return;
    }

    System.out.print("Novo nome do cliente: ");
    String cliente = scanner.nextLine().trim();

    System.out.print("Nova descrição: ");
    String descricao = scanner.nextLine().trim();

    System.out.print("Novo valor (ex: 59.90): ");
    double valor;
    try {
      valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
    } catch (NumberFormatException e) {
      System.out.println("Valor inválido. Operação cancelada.");
      return;
    }

    try {
      service.atualizarPedido(id, cliente, descricao, valor);
      System.out.println("Pedido atualizado com sucesso!");
    } catch (RuntimeException e) {
      System.out.println("Erro ao atualizar: " + e.getMessage());
    }
  }

  // 5. Marcar como Pago
  private void marcarPago() {
    System.out.print("Digite o ID do pedido: ");
    int id;
    try {
      id = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("ID inválido.");
      return;
    }

    try {
      service.marcarComoPago(id);
      System.out.println("Pedido marcado como PAGO com sucesso!");
    } catch (RuntimeException e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }

  // 6. Marcar como Enviado
  private void marcarEnviado() {
    System.out.print("Digite o ID do pedido: ");
    int id;
    try {
      id = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("ID inválido.");
      return;
    }

    try {
      service.marcarComoEnviado(id);
      System.out.println("Pedido marcado como ENVIADO com sucesso!");
    } catch (RuntimeException e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }

  // 7. Cancelar Pedido
  private void cancelarPedido() {
    System.out.print("Digite o ID do pedido: ");
    int id;
    try {
      id = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("ID inválido.");
      return;
    }

    try {
      service.cancelarPedido(id);
      System.out.println("Pedido CANCELADO com sucesso!");
    } catch (RuntimeException e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }

  // 8. Remover Pedido (com confirmação)
  private void removerPedido() {
    System.out.print("Digite o ID do pedido a remover: ");
    int id;
    try {
      id = Integer.parseInt(scanner.nextLine().trim());
    } catch (NumberFormatException e) {
      System.out.println("ID inválido.");
      return;
    }

    System.out.print("Tem certeza que deseja remover o pedido " + id + "? (s/n): ");
    String confirmacao = scanner.nextLine().trim();

    if (!confirmacao.equalsIgnoreCase("s")) {
      System.out.println("Operação cancelada.");
      return;
    }

    try {
      service.removerPedido(id);
      System.out.println("Pedido removido com sucesso!");
    } catch (RuntimeException e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }
}
