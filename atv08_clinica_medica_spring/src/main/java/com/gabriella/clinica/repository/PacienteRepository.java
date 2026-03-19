package com.gabriella.clinica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabriella.clinica.entity.paciente.Paciente;
import com.gabriella.clinica.entity.paciente.Sexo;
import com.gabriella.clinica.entity.paciente.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Carrega paciente com ficha em um único SELECT — evita query extra
    @Query("SELECT p FROM Paciente p LEFT JOIN FETCH p.fichaMedica WHERE p.id = :id")
    Optional<Paciente> findByIdComFicha(@Param("id") Long id);

    // Busca por CPF
    Optional<Paciente> findByCpf(String cpf);

    // Busca por nome ou e-mail
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :t, '%')) OR LOWER(p.email) LIKE LOWER(CONCAT('%', :t, '%'))")
    List<Paciente> buscarPorTermo(@Param("t") String termo);

    // Pacientes por tipo sanguíneo
    @Query("SELECT p FROM Paciente p JOIN p.fichaMedica f WHERE f.tipoSanguineo = :tipo")
    List<Paciente> findByTipoSanguineo(@Param("tipo") String tipo);

    // Busca por email
    Optional<Paciente> findByEmail(String email);

    // Busca por status
    Optional<List<Paciente>> findByStatus(Status status);


    // QUERY ORIENTADA A MÉTODO

    // List<Paciente> findBySexo(Sexo sexo);

    // List<Paciente> findByDataEntradaBefore(LocalDate data);

    // List<Paciente> findByDataEntradaBetween(LocalDate hoje, LocalDate
    // dataFutura);

    // List<Paciente>
    // findByNomeHospedeContainingIgnoreCaseOrEmailContainingIgnoreCase(String
    // termo1, String termo2);

    // List<Paciente> findByStatus(Status status);

    // // QUERY JPQL

    // @Query("SELECT r FROM Paciente r")
    // List<Paciente> retornarTodasPacientes();

    // @Query("SELECT r FROM Paciente r WHERE r.dataCheckOut IS NULL")
    // List<Paciente> naoConcluidas();

    // // QUERY NATIVE SQL

    // @Query(nativeQuery = true, value = "SELECT * FROM PACIENTE WHERE
    // DATA_CHECK_OUT IS NULL")
    // List<Paciente> naoConcluidasNative();

    // // QUERY ORIENTADA A MÉTODO (status hospedagem)

    // List<Paciente> findByDataCheckOutIsNull();

    // // QUERY NATIVE POR STATUS

    // @Query(value = "SELECT * FROM PACIENTE WHERE STATUS = :status", nativeQuery =
    // true)
    // List<Paciente> procurarPorStatus(Status status);

    // // QUERY NATIVE POR TIPO DE QUARTO

    // @Query(value = "SELECT * FROM PACIENTE WHERE SEXO = :sexo", nativeQuery =
    // true)
    // List<Paciente> procurarPorSexo(Sexo sexo);

    // // RESERVAS QUE DEVEM FAZER CHECK-IN HOJE

    // @Query(value = "SELECT * FROM PACIENTE WHERE DATA_ENTRADA = CURRENT_DATE",
    // nativeQuery = true)
    // List<Paciente> procurarReservasHoje();
}