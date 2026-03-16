package br.com.gabriella.tarefa.repository;

import com.gabriellaluiz.reservahotel.entity.Reserva;
import com.gabriellaluiz.reservahotel.entity.Status;
import com.gabriellaluiz.reservahotel.entity.TipoQuarto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // QUERY ORIENTADA A MÉTODO

    List<Reserva> findByTipoQuarto(TipoQuarto tipoQuarto);

    List<Reserva> findByDataEntradaBefore(LocalDate data);

    List<Reserva> findByDataEntradaBetween(LocalDate hoje, LocalDate dataFutura);

    List<Reserva> findByNomeHospedeContainingIgnoreCaseOrEmailHospedeContainingIgnoreCase(String termo1, String termo2);

    List<Reserva> findByStatus(Status status);


    // QUERY JPQL

    @Query("SELECT r FROM Reserva r")
    List<Reserva> retornarTodasReservas();

    @Query("SELECT r FROM Reserva r WHERE r.dataCheckOut IS NULL")
    List<Reserva> naoConcluidas();


    // QUERY NATIVE SQL

    @Query(nativeQuery = true, value = "SELECT * FROM RESERVA WHERE DATA_CHECK_OUT IS NULL")
    List<Reserva> naoConcluidasNative();


    // QUERY ORIENTADA A MÉTODO (status hospedagem)

    List<Reserva> findByDataCheckOutIsNull();


    // QUERY NATIVE POR STATUS

    @Query(value = "SELECT * FROM RESERVA WHERE STATUS = :status", nativeQuery = true)
    List<Reserva> procurarPorStatus(Status status);


    // QUERY NATIVE POR TIPO DE QUARTO

    @Query(value = "SELECT * FROM RESERVA WHERE TIPO_QUARTO = :tipo", nativeQuery = true)
    List<Reserva> procurarPorTipoQuarto(TipoQuarto tipo);


    // RESERVAS QUE DEVEM FAZER CHECK-IN HOJE

    @Query(value = "SELECT * FROM RESERVA WHERE DATA_ENTRADA = CURRENT_DATE", nativeQuery = true)
    List<Reserva> procurarReservasHoje();

}