package com.gabriella.barbearia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import com.gabriella.barbearia.model.Cliente;

import java.util.UUID;

public record ClienteDTO(
        UUID id,

        @NotBlank(message = "O nome é obrigatório") String nome,

        @NotBlank(message = "O email é obrigatório") @Email(message = "Email deve ser válido") String email,

        @NotBlank(message = "O telefone é obrigatório") String telefone

) {

    // Mapeamento: DTO -> Entity
    public Cliente toEntity() {
        Cliente cliente = new Cliente();
        cliente.setId(this.id);
        cliente.setNome(this.nome);
        cliente.setEmail(this.email);
        cliente.setTelefone(this.telefone);
        return cliente;
    }

    // Mapeamento manual: Entity -> DTO
    public static ClienteDTO fromEntity(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getTelefone());
    }

    // Getters para acesso aos campos do record
    public UUID id() {
        return id;
    }

    public String nome() {
        return nome;
    }

    public String email() {
        return email;
    }

    public String telefone() {
        return telefone;
    }
}
