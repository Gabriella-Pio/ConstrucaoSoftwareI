package main.java.com.gabriella.pedidos.service;

import main.java.com.gabriella.pedidos.repository.ClienteRepository;

@Service
public class PedidoService {
  
  private final PedidoRepository pedidoRepository;
  private final ClienteRepository clienteRepository;

  public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
    this.pedidoRepository = pedidoRepository;
    this.clienteRepository = clienteRepository;
  }

  // Salvar
  public Pedido save(Pedido pedido) {
    var cliente = clienteRepository.findById(pedido.getCliente().getId())
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    pedido.setCliente(cliente);

    return pedidoRepository.save(pedido);
  }

  // Listar
  public List<Pedido> findAll() {
    return pedidoRepository.findAll();
  }

  // Buscar por ID
  public Pedido findById(UUID id) {
    return pedidoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
  }

  // Deletar
  public void deleteById(UUID id) {
    var pedido = pedidoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    pedidoRepository.delete(pedido);
  }
  
}
