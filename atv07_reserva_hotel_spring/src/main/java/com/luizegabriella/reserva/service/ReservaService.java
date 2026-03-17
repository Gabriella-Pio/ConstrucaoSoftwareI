package com.luizegabriella.reserva.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.luizegabriella.reserva.entity.*;
import com.luizegabriella.reserva.repository.ReservaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservaService {

    private final ReservaRepository repository;

    public ReservaService(ReservaRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Reserva criar(Reserva reserva) {

        reserva.setDataCriacao(LocalDateTime.now());
        reserva.setStatus(Status.PENDENTE);
        reserva.setDataCheckIn(null);
        reserva.setDataCheckOut(null);

        if (reserva.getDataEntrada().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Data de entrada deve ser hoje ou futura");
        }

        if (!reserva.getDataSaida().isAfter(reserva.getDataEntrada())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Data de saída deve ser posterior à data de entrada");
        }

        return repository.save(reserva);
    }

    // LISTAR TODAS
    public List<Reserva> listar() {
        return repository.findAll();
    }

    // BUSCAR POR ID
    public Reserva buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva não encontrada"));
    }

    // FILTRAR POR STATUS
    public List<Reserva> buscarPorStatus(Status status) {
        return repository.findByStatus(status);
    }

    // FILTRAR POR TIPO DE QUARTO
    public List<Reserva> buscarPorTipo(TipoQuarto tipo) {
        return repository.findByTipoQuarto(tipo);
    }

    // RESERVAS HOJE
    public List<Reserva> reservasHoje() {
        return repository.findByDataEntradaBefore(LocalDate.now());
    }

    // RESERVAS PRÓXIMAS
    public List<Reserva> proximasReservas(int dias) {
        LocalDate inicio = LocalDate.now();
        LocalDate fim = inicio.plusDays(dias);

        return repository.findByDataEntradaBetween(inicio, fim);
    }

    // BUSCA POR NOME OU EMAIL
    public List<Reserva> buscar(String termo) {
        return repository
                .findByNomeHospedeContainingIgnoreCaseOrEmailContainingIgnoreCase(termo, termo);
    }

    // EM HOSPEDAGEM
    public List<Reserva> emHospedagem() {
        return repository.findByStatus(Status.EM_HOSPEDAGEM);
    }

    // CONFIRMAR RESERVA
    public Reserva confirmar(Long id) {

        Reserva reserva = buscarPorId(id);

        if (reserva.getStatus() != Status.PENDENTE) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Reserva não pode ser confirmada");
        }

        reserva.setStatus(Status.CONFIRMADA);

        return repository.save(reserva);
    }

    // CHECK-IN
    public Reserva checkin(Long id) {

        Reserva reserva = buscarPorId(id);

        if (reserva.getStatus() != Status.CONFIRMADA) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Reserva precisa estar CONFIRMADA");
        }

        if (LocalDate.now().isBefore(reserva.getDataEntrada())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Check-in não permitido antes da data de entrada");
        }

        reserva.setStatus(Status.EM_HOSPEDAGEM);
        reserva.setDataCheckIn(null);

        return repository.save(reserva);
    }

    // CHECKOUT
    public Reserva checkout(Long id) {

        Reserva reserva = buscarPorId(id);

        if (reserva.getStatus() != Status.EM_HOSPEDAGEM) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Reserva não está em hospedagem");
        }

        reserva.setStatus(Status.CONCLUIDA);
        reserva.setDataCheckOut(null);

        return repository.save(reserva);
    }

    // ATUALIZAR
    public Reserva atualizar(Long id, Reserva novaReserva) {
        Reserva reserva = buscarPorId(id);

        if (reserva.getStatus() == Status.EM_HOSPEDAGEM) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Reserva em hospedagem não pode ser atualizada");
        }

        reserva.setNomeHospede(novaReserva.getNomeHospede());
        reserva.setEmail(novaReserva.getEmail());
        reserva.setDataEntrada(novaReserva.getDataEntrada());
        reserva.setDataSaida(novaReserva.getDataSaida());
        reserva.setTipoQuarto(novaReserva.getTipoQuarto());
        reserva.setObservacoes(novaReserva.getObservacoes());

        return repository.save(reserva);
    }

    // CANCELAR
    public Reserva cancelar(Long id) {

        Reserva reserva = buscarPorId(id);

        if (reserva.getStatus() == Status.EM_HOSPEDAGEM ||
            reserva.getStatus() == Status.CONCLUIDA) {

            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Reserva não pode ser cancelada");
        }

        reserva.setStatus(Status.CANCELADA);

        return repository.save(reserva);
    }

    // DELETE
    public void deletar(Long id) {

        Reserva reserva = buscarPorId(id);

        if (reserva.getStatus() == Status.EM_HOSPEDAGEM) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Reserva em hospedagem não pode ser excluída");
        }

        repository.delete(reserva);
    }

}