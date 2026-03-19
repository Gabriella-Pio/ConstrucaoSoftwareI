package com.gabriella.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriella.clinica.entity.ficha.FichaMedica;

public interface FichaMedicaRepository extends JpaRepository<FichaMedica, Long> {}
