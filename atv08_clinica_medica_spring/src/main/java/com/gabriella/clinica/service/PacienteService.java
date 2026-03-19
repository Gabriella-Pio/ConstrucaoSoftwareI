package com.gabriella.clinica.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.gabriella.clinica.entity.*;
import com.gabriella.clinica.entity.ficha.FichaMedica;
import com.gabriella.clinica.entity.paciente.Paciente;
import com.gabriella.clinica.entity.paciente.Status;
import com.gabriella.clinica.repository.PacienteRepository;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Paciente salvar(Paciente paciente) {

        paciente.setDataCadastro(LocalDateTime.now());
        paciente.setStatusPaciente(Status.ATIVO);

        if (paciente.getDataNascimento().isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Data de nascimento deve ser hoje ou no passado");
        }

        if (repository.findByCpf(paciente.getCpf()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "CPF já cadastrado");
        }

        if (repository.findByEmail(paciente.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Email já cadastrado");
        }

        return repository.save(paciente);
    }

    // LISTAR TODOS
    public List<Paciente> listar() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Paciente findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));
    }

    // BUSCAR POR ID COM FICHA MÉDICA
    public Paciente findByIdComFicha(Long id) {
        return repository.findByIdComFicha(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));
    }

    // BUSCAR POR CPF
    public Paciente findByCpf(String cpf) {
        return repository.findByCpf(cpf)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));
    }

    // ATUALIZAR
    public Paciente atualizar(Long id, Paciente novoPaciente) {
        Paciente paciente = findById(id);

        if (paciente.getStatusPaciente() == Status.SUSPENSO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Paciente suspenso não pode ser atualizado");
        }

        paciente.setNome(novoPaciente.getNome());
        // Comentado pq não faz sentido altera o CPF, mas pode ser reativado se quiserem
        // permitir isso
        // paciente.setCpf(novoPaciente.getCpf());
        paciente.setEmail(novoPaciente.getEmail());
        paciente.setTelefone(novoPaciente.getTelefone());
        paciente.setDataNascimento(novoPaciente.getDataNascimento());
        paciente.setSexo(novoPaciente.getSexo());
        paciente.setStatusPaciente(novoPaciente.getStatusPaciente());
        // Comentado pq não faz sentido alterar a data de cadastro, mas pode ser
        // reativado se quiserem permitir isso
        // paciente.setDataCadastro(novoPaciente.getDataCadastro());

        return repository.save(paciente);
    }

    // DELETE
    public void deletar(Long id) {

        Paciente paciente = findById(id);

        if (paciente.getStatusPaciente() == Status.ATIVO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Paciente ativo não pode ser excluído");
        }

        repository.delete(paciente);
    }

    // FILTRAR POR STATUS
    public List<Paciente> findByStatus(Status status) {
        return repository.findByStatus(status)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Nenhum paciente encontrado com status: " + status));
    }

    // ATIVAR PACIENTE
    public Paciente ativar(Long id) {
        Paciente paciente = findById(id);
        if (paciente.getStatusPaciente() == Status.ATIVO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Paciente já está ativo");
        }
        paciente.setStatusPaciente(Status.ATIVO);
        return repository.save(paciente);
    }

    // INATIVAR PACIENTE
    public Paciente inativar(Long id) {
        Paciente paciente = findById(id);
        if (paciente.getStatusPaciente() == Status.INATIVO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Paciente já está inativo");
        }
        paciente.setStatusPaciente(Status.INATIVO);
        return repository.save(paciente);
    }

    // SUSPENDER PACIENTE
    public Paciente suspender(Long id) {
        Paciente paciente = findById(id);
        if (paciente.getStatusPaciente() == Status.SUSPENSO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Paciente já está suspenso");
        } else if (paciente.getStatusPaciente() == Status.INATIVO) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Paciente inativo não pode ser suspenso");
        }
        paciente.setStatusPaciente(Status.SUSPENSO);
        return repository.save(paciente);
    }

    // // PacienteS HOJE
    // public List<Paciente> pacientesHoje() {
    //     return repository.findByDataEntradaBefore(LocalDate.now());
    // }

    // // RESERVAS PRÓXIMAS
    // public List<Paciente> proximasPacientes(int dias) {
    //     LocalDate inicio = LocalDate.now();
    //     LocalDate fim = inicio.plusDays(dias);

    //     return repository.findByDataEntradaBetween(inicio, fim);
    // }

    // // DETALHES DA ESTADIA
    // @Transactional
    // public Paciente vincularDetalhes(Long id, FichaMedica detalhes) {
    //     Paciente paciente = buscarPorId(id);
    //     if (paciente.getStatus() == Status.CANCELADA || paciente.getStatus() == Status.CONCLUIDA) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT, "Paciente inválido para detalhes");
    //     }
    //     detalhes.setPaciente(paciente);
    //     paciente.setDetalhes(detalhes);
    //     return repository.save(paciente);
    // }

    // BUSCA POR NOME OU EMAIL
    public List<Paciente> buscar(String termo) {
        return repository.buscarPorTermo(termo);
    }

    // // EM HOSPEDAGEM
    // public List<Paciente> emHospedagem() {
    //     return repository.findByStatus(Status.EM_HOSPEDAGEM);
    // }

    // // CONFIRMAR RESERVA
    // public Paciente confirmar(Long id) {

    //     Paciente paciente = buscarPorId(id);

    //     if (paciente.getStatus() != Status.PENDENTE) {
    //         throw new ResponseStatusException(HttpStatus.CONFLICT,
    //                 "Reserva não pode ser confirmada");
    //     }

    //     paciente.setStatus(Status.CONFIRMADA);

    //     return repository.save(paciente);
    // }

    // // CANCELAR
    // public Paciente cancelar(Long id) {

    //     Paciente paciente = buscarPorId(id);

    //     if (paciente.getStatus() == Status.EM_HOSPEDAGEM ||
    //             paciente.getStatus() == Status.CONCLUIDA) {

    //         throw new ResponseStatusException(HttpStatus.CONFLICT,
    //                 "Paciente não pode ser cancelado");
    //     }

    //     paciente.setStatus(Status.CANCELADA);

    //     return repository.save(paciente);
    // }

}