package com.gabriella.barbearia.dto;

import com.gabriella.barbearia.model.Barbeiro;
import com.gabriella.barbearia.model.enums.Especialidade;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record BarbeiroDTO(
        UUID id,
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotNull(message = "A especialidade é obrigatória") Especialidade especialidade) {

    // Mapeamento: DTO -> Entity
    public Barbeiro toEntity() {
        Barbeiro barbeiro = new Barbeiro();
        barbeiro.setId(this.id);
        barbeiro.setNome(this.nome);
        barbeiro.setEspecialidade(this.especialidade);
        return barbeiro;
    }

    // Mapeamento: Entity -> DTO
    public static BarbeiroDTO fromEntity(Barbeiro barbeiro) {
        return new BarbeiroDTO(
                barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getEspecialidade());
    }
}