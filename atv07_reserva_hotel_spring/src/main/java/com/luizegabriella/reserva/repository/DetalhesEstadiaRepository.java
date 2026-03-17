package com.luizegabriella.reserva.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luizegabriella.reserva.entity.DetalhesEstadia;

public interface DetalhesEstadiaRepository extends JpaRepository<DetalhesEstadia, Long> {
  
}
