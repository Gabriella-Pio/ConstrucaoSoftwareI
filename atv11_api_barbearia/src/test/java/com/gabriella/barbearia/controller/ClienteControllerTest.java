package com.gabriella.barbearia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriella.barbearia.dto.ClienteDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ClienteControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void deveCadastrarEListarClientes() throws Exception {
    ClienteDTO novoCliente = new ClienteDTO(null, "Gabriella", "gabriella@email.com", "62999999999");

    mockMvc.perform(post("/api/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(novoCliente)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.email").value("gabriella@email.com"));

    mockMvc.perform(get("/api/clientes"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content[0].nome").value("Gabriella"));
  }

  @Test
  void naoDevePermitirEmailDuplicado() throws Exception {
    ClienteDTO dto = new ClienteDTO(null, "Teste", "duplicado@email.com", "6212345678");

    mockMvc.perform(post("/api/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isCreated());

    mockMvc.perform(post("/api/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isConflict());
  }
}