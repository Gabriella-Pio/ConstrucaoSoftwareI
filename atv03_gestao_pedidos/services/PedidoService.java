package atividade03.services;

import atividade03.entity.Pedido;
import atividade03.entity.StatusPedido;
import atividade03.repository.PedidoRepository;

import java.util.List;
import java.util.Optional;

public class PedidoService {

  private final PedidoRepository repository;

  public PedidoService(PedidoRepository repository) {
    this.repository = repository;
  }

  // 1. Cadastrar Pedido
  public void cadastrarPedido(String cliente, String descricao, double valor) {
    Pedido novo = new Pedido(cliente, descricao, valor);
    repository.salvar(novo);
  }

  // 2. Listar todos os pedidos
  public List<Pedido> listarPedidos() {
    return repository.listarTodos();
  }

  // 3. Buscar pedido por ID
  public Optional<Pedido> buscarPedidoPorId(int id) {
    return repository.buscarPorId(id);
  }

  // 4. Atualizar pedido (não permitido se estiver CANCELADO)
  public void atualizarPedido(int id, String novoCliente, String novaDescricao, double novoValor) {
    Pedido pedido = repository.buscarPorId(id)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

    if (pedido.getStatus() == StatusPedido.CANCELADO) {
      throw new IllegalStateException("Não é possível atualizar um pedido CANCELADO.");
    }

    pedido.setCliente(novoCliente);
    pedido.setDescricao(novaDescricao);
    pedido.setValor(novoValor);
    repository.atualizar(pedido);
  }

  // 5. Marcar como PAGO
  public void marcarComoPago(int id) {
    Pedido pedido = repository.buscarPorId(id)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

    if (pedido.getStatus() != StatusPedido.CRIADO) {
      throw new IllegalStateException("Só pode pagar pedidos no status CRIADO.");
    }
    pedido.setStatus(StatusPedido.PAGO);
    repository.atualizar(pedido);
  }

  // 6. Marcar como ENVIADO
  public void marcarComoEnviado(int id) {
    Pedido pedido = repository.buscarPorId(id)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

    if (pedido.getStatus() != StatusPedido.PAGO) {
      throw new IllegalStateException("Só pode enviar pedidos já PAGOS.");
    }
    pedido.setStatus(StatusPedido.ENVIADO);
    repository.atualizar(pedido);
  }

  // 7. Cancelar pedido
  public void cancelarPedido(int id) {
    Pedido pedido = repository.buscarPorId(id)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

    if (pedido.getStatus() == StatusPedido.ENVIADO) {
      throw new IllegalStateException("Não é possível cancelar um pedido já ENVIADO.");
    }
    if (pedido.getStatus() == StatusPedido.CANCELADO) {
      throw new IllegalStateException("O pedido já está CANCELADO.");
    }
    pedido.setStatus(StatusPedido.CANCELADO);
    repository.atualizar(pedido);
  }

  // 8. Remover pedido
  public boolean removerPedido(int id) {
    repository.buscarPorId(id)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));
    return repository.deletar(id);
  }
}
