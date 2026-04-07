package com.gabriella.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriella.barbearia.model.Servico;
import java.util.UUID;

public interface ServicoRepository extends JpaRepository<Servico, UUID> {
}
