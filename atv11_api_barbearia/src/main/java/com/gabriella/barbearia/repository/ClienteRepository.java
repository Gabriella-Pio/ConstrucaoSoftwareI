package com.gabriella.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriella.barbearia.model.Cliente;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

  boolean existsByEmail(String email);

  Optional<Cliente> findByEmail(String email);
}
