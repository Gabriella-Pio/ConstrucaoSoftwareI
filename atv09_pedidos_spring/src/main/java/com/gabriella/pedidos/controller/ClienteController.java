package com.gabriella.pedidos.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gabriella.pedidos.entity.Cliente;
import com.gabriella.pedidos.entity.Pedidos;
import com.gabriella.pedidos.service.ClienteService;

import org.springframework.web.bind.annotation.*;

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

  @PatchMapping("/{id}")
  public ResponseEntity<Cliente> update(@PathVariable UUID id, @RequestBody Pedidos pedido) {
    var updatedCliente = clienteService.novoPedido(id, pedido);
    return ResponseEntity.ok(updatedCliente);
  }
}
