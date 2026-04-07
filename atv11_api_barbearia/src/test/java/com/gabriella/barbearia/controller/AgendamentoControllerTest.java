package com.gabriella.barbearia.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriella.barbearia.dto.*;
import com.gabriella.barbearia.model.*;
import com.gabriella.barbearia.model.enums.*;
import com.gabriella.barbearia.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AgendamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private BarbeiroRepository barbeiroRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ServicoRepository servicoRepository;

    private UUID clienteId;
    private UUID barbeiroId;
    private UUID servicoId;

    @BeforeEach
    void setup() {
        // Criar Cliente
        Cliente c = clienteRepository.save(new Cliente("Joao", "joao@test.com", "123456"));
        clienteId = c.getId();

        // Criar Barbeiro com Horário de Trabalho (Segunda-feira, 08:00 - 12:00)
        Barbeiro b = new Barbeiro();
        b.setNome("Barbeiro Mestre");
        b.setEspecialidade(Especialidade.CORTE);

        HorarioTrabalho h = new HorarioTrabalho();
        h.setDiaSemana(DiaSemana.SEGUNDA);
        h.setHoraInicio(LocalTime.of(8, 0));
        h.setHoraFim(LocalTime.of(12, 0));
        h.setBarbeiro(b);
        b.getHorariosTrabalho().add(h);

        barbeiroId = barbeiroRepository.save(b).getId();

        // Criar Serviço de 30 minutos
        Servico s = servicoRepository.save(new Servico("Corte Normal", 50.0, 30));
        servicoId = s.getId();
    }

    @Test
    void deveCriarAgendamentoComSucesso() throws Exception {
        // Segunda-feira às 09:00 (Dentro do expediente)
        LocalDateTime dataHora = LocalDateTime.of(2026, 4, 13, 9, 0);
        AgendamentoDTO dto = new AgendamentoDTO(null, dataHora, null, clienteId, barbeiroId, servicoId);

        mockMvc.perform(post("/api/agendamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    void naoDevePermitirAgendamentosSobrepostos() throws Exception {
        LocalDateTime dataHora = LocalDateTime.of(2026, 4, 13, 9, 0);
        AgendamentoDTO dto = new AgendamentoDTO(null, dataHora, null, clienteId, barbeiroId, servicoId);

        // Primeiro agendamento (09:00 - 09:30)
        mockMvc.perform(post("/api/agendamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        // Segundo agendamento tentando o mesmo horário (Conflito esperado: 409)
        mockMvc.perform(post("/api/agendamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isConflict());
    }

    @Test
    void naoDeveAgendarForaDoExpediente() throws Exception {
        // Domingo (Barbeiro só trabalha MONDAY)
        LocalDateTime dataHora = LocalDateTime.of(2026, 4, 12, 9, 0);
        AgendamentoDTO dto = new AgendamentoDTO(null, dataHora, null, clienteId, barbeiroId, servicoId);

        mockMvc.perform(post("/api/agendamentos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}