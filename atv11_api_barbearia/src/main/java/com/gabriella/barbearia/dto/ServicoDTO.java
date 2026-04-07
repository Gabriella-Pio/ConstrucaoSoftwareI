package com.gabriella.barbearia.dto;

import com.gabriella.barbearia.model.Servico;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ServicoDTO(
        UUID id,

        @NotBlank(message = "Nome do serviço é obrigatório") String nome,

        @NotNull(message = "O preço é obrigatório") @Min(value = 0, message = "O preço não pode ser negativo") Double preco,

        @NotNull(message = "A duração é obrigatória") @Min(value = 1, message = "A duração mínima é de 1 minuto") Integer duracaoMinutos) {

    public Servico toEntity() {
        Servico servico = new Servico();
        servico.setId(this.id);
        servico.setNome(this.nome);
        servico.setPreco(this.preco);
        servico.setDuracaoMinutos(this.duracaoMinutos);
        return servico;
    }

    public static ServicoDTO fromEntity(Servico servico) {
        return new ServicoDTO(
                servico.getId(),
                servico.getNome(),
                servico.getPreco(),
                servico.getDuracaoMinutos());
    }
}