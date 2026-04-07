package com.gabriella.barbearia.dto;

import com.gabriella.barbearia.model.Agendamento;
import com.gabriella.barbearia.model.enums.Status;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record AgendamentoDTO(
        UUID id,

        @NotNull(message = "A data e hora são obrigatórias") @Future(message = "A data deve ser no futuro") LocalDateTime dataHora,

        Status status,

        @NotNull(message = "O ID do cliente é obrigatório") UUID clienteId,

        @NotNull(message = "O ID do barbeiro é obrigatório") UUID barbeiroId,

        @NotNull(message = "O ID do serviço é obrigatório") UUID servicoId) {

    public static AgendamentoDTO fromEntity(Agendamento agendamento) {
        return new AgendamentoDTO(
                agendamento.getId(),
                agendamento.getDataHora(),
                agendamento.getStatus(),
                agendamento.getCliente().getId(),
                agendamento.getBarbeiro().getId(),
                agendamento.getServico().getId());
    }
}