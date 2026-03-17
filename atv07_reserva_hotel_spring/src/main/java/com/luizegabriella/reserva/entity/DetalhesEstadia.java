package com.luizegabriella.reserva.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "TB_DETALHES_ESTADIA")
public class DetalhesEstadia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  private String numeroQuarto;

  @NotNull
  @Min(1)
  private Integer andar;


  private boolean possuiFrigobar;

  private boolean possuiVaranda;

  private boolean acessibilidade;

  @Size(max = 300)
  private String observacoesQuarto;

  
}
