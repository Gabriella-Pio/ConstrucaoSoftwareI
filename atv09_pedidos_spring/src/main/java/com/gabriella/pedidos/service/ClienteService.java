package main.java.com.gabriella.pedidos.service;


@Service
public class ClienteService {
  
  @Autowired
  private ClienteRepository clienteRepository;

  // Salvar
  public Cliente save(Cliente cliente) {

    return clienteRepository.save(cliente);
  }

  public Cliente novoPedido(UUID clienteId, Pedido pedido) {
    var cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    cliente.addPedido(pedido);
    return clienteRepository.save(cliente);
  }

}
