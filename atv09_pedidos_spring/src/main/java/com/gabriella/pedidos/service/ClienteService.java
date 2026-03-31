package com.gabriella.pedidos.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriella.pedidos.entity.Cliente;
import com.gabriella.pedidos.entity.Pedidos;
import com.gabriella.pedidos.repository.ClienteRepository;
import com.gabriella.pedidos.repository.PedidoRepository;

@Service
public class ClienteService {
  
  @Autowired
  private ClienteRepository clienteRepository;

  // Salvar
  public Cliente save(Cliente cliente) {

    return clienteRepository.save(cliente);
  }

  public Cliente novoPedido(UUID clienteId, Pedidos pedido) {
    var cliente = clienteRepository.findById(clienteId)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    cliente.addPedido(pedido);
    return clienteRepository.save(cliente);
  }

  public Cliente findById(UUID id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
  }

  public List<Cliente> findAll() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAll'");
  }

}
