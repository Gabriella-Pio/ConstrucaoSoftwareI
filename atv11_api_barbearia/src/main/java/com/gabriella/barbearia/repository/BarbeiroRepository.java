package com.gabriella.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriella.barbearia.model.Barbeiro;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.UUID;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, UUID> {}