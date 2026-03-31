package com.gabriella.pedidos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriella.pedidos.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
  
}
