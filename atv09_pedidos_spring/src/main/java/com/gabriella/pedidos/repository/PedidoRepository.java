package com.gabriella.pedidos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriella.pedidos.entity.Pedidos;

public interface PedidoRepository extends JpaRepository<Pedidos, UUID> {
  
}
