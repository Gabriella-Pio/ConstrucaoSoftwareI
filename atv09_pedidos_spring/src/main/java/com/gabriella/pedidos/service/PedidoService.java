package com.gabriella.pedidos.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gabriella.pedidos.repository.PedidoRepository;
import com.gabriella.pedidos.repository.ClienteRepository;
import com.gabriella.pedidos.entity.Pedidos;
import com.gabriella.pedidos.entity.Cliente;
@Service
public class PedidoService {
  
  private final PedidoRepository pedidoRepository;
  private final ClienteRepository clienteRepository;

  public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
    this.pedidoRepository = pedidoRepository;
    this.clienteRepository = clienteRepository;
  }

  // Salvar
  public Pedidos save(Pedidos pedido) {
    var cliente = clienteRepository.findById(pedido.getCliente().getId())
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    pedido.setCliente(cliente);

    return pedidoRepository.save(pedido);
  }

  // Listar
  public List<Pedidos> findAll() {
    return pedidoRepository.findAll();
  }

  // Buscar por ID
  public Pedidos findById(UUID id) {
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
