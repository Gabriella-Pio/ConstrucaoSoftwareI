package main.java.com.gabriella.pedidos.service;


@Service
public class ClienteService {
  
  @Autowired
  private ClienteRepository clienteRepository;

  // Salvar
  public Cliente save(Cliente cliente) {

    return clienteRepository.save(cliente);
  }

  

}
