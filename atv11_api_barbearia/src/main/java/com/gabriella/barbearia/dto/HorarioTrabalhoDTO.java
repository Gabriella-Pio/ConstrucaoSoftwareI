package com.gabriella.barbearia.dto;

import com.gabriella.barbearia.model.HorarioTrabalho;
import com.gabriella.barbearia.model.enums.DiaSemana;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.UUID;

public record HorarioTrabalhoDTO(
    UUID id,
    @NotNull(message = "O dia da semana é obrigatório") DiaSemana diaSemana,
    @NotNull(message = "A hora de início é obrigatória") LocalTime horaInicio,
    @NotNull(message = "A hora de término é obrigatória") LocalTime horaFim,
    @NotNull(message = "O ID do barbeiro é obrigatório") UUID barbeiroId
) {
    public static HorarioTrabalhoDTO fromEntity(HorarioTrabalho horario) {
        return new HorarioTrabalhoDTO(
            horario.getId(),
            horario.getDiaSemana(),
            horario.getHoraInicio(),
            horario.getHoraFim(),
            horario.getBarbeiro().getId()
        );
    }
}