package com.gabriella.barbearia.service;

import com.gabriella.barbearia.dto.ClienteDTO;
import com.gabriella.barbearia.model.Cliente;
import com.gabriella.barbearia.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class ClienteService {

  private final ClienteRepository clienteRepository;

  public ClienteService(ClienteRepository clienteRepository) {
    this.clienteRepository = clienteRepository;
  }

  // Listar todos os clientes (Paginado e convertido para DTO)
  public Page<ClienteDTO> findAll(Pageable pageable) {
    return clienteRepository.findAll(pageable)
        .map(ClienteDTO::fromEntity);
  }

  // Buscar cliente por ID (Retorna a entidade interna para uso em outros
  // Services)
  public Cliente findByIdInternal(UUID id) {
    return clienteRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
  }

  // Buscar cliente por ID (Retorna DTO para o Controller)
  public ClienteDTO findById(UUID id) {
    return ClienteDTO.fromEntity(findByIdInternal(id));
  }

  // Cadastrar novo cliente
  @Transactional
  public ClienteDTO save(ClienteDTO dto) {
    // Verifica se o email já existe usando o Repository
    if (clienteRepository.existsByEmail(dto.email())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado");
    }

    Cliente cliente = dto.toEntity();
    return ClienteDTO.fromEntity(clienteRepository.save(cliente));
  }

  // Atualizar cliente existente
  @Transactional
  public ClienteDTO update(UUID id, ClienteDTO dto) {
    Cliente existingCliente = findByIdInternal(id);

    // Se o email mudou, verifica se o novo email já está em uso por outra pessoa
    if (!existingCliente.getEmail().equals(dto.email()) && clienteRepository.existsByEmail(dto.email())) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "O novo e-mail já está em uso");
    }

    existingCliente.setNome(dto.nome());
    existingCliente.setEmail(dto.email());
    existingCliente.setTelefone(dto.telefone());

    return ClienteDTO.fromEntity(clienteRepository.save(existingCliente));
  }

  // Deletar cliente
  @Transactional
  public void delete(UUID id) {
    Cliente cliente = findByIdInternal(id);
    clienteRepository.delete(cliente);
  }
}