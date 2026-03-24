package main.java.com.gabriella.pedidos.controller;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
  
  @Autowired
  private ClienteService clienteService;

  @PostMapping
  public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
    var savedCliente = clienteService.save(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedCliente);
  }

  @GetMapping
  public List<Cliente> findAll() {
    return clienteService.findAll();
  }

  @GetMapping("/{id}")
  public Cliente findById(@PathVariable UUID id) {
    return clienteService.findById(id);
  }
}
