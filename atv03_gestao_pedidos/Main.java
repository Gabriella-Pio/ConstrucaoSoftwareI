package atividade03;

import atividade03.controller.PedidoController;
import atividade03.repository.PedidoRepository;
import atividade03.repository.PedidoRepositoryMemory;
import atividade03.services.PedidoService;

public class Main {
  public static void main(String[] args) {
    PedidoRepository repository = new PedidoRepositoryMemory();
    PedidoService service = new PedidoService(repository);
    PedidoController controller = new PedidoController(service);
    controller.executar();
  }
}
