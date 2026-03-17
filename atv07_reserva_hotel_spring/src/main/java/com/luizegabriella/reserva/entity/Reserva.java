package com.luizegabriella.reserva.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "TB_RESERVA")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(name = "nome", length = 100)
    private String nomeHospede;

    @NotBlank
    @Email
    @Column(name = "email")
    private String email;

    @Size(max = 20)
    @Column(name = "telefone", length = 20)
    private String telefone;

    @NotNull
    @FutureOrPresent
    @Column(name = "data_entrada")
    private LocalDate dataEntrada;

    @NotNull
    @Future
    @Column(name = "data_saida")
    private LocalDate dataSaida;

    @Column(name = "data_check_in")
    private LocalDateTime dataCheckIn;

    @Column(name = "data_check_out")
    private LocalDateTime dataCheckOut;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_quarto")
    private TipoQuarto tipoQuarto;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Size(max = 500)
    @Column(name = "observacoes", length = 500)
    private String observacoes;

    // Contrutor Vazio
    public Reserva() {

    }

    public Reserva(Long id, String nomeHospede, String email, String telefone, LocalDate dataEntrada,
            LocalDate dataSaida, LocalDateTime dataCheckIn, LocalDateTime dataCheckOut, LocalDateTime dataCriacao,
            TipoQuarto tipoQuarto, Status status, String observacoes) {
        this.id = id;
        this.nomeHospede = nomeHospede;
        this.email = email;
        this.telefone = telefone;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.dataCriacao = dataCriacao;
        this.tipoQuarto = tipoQuarto;
        this.status = status;
        this.observacoes = observacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id)
                && Objects.equals(nomeHospede, reserva.nomeHospede)
                && Objects.equals(email, reserva.email)
                && Objects.equals(telefone, reserva.telefone)
                && Objects.equals(dataEntrada, reserva.dataEntrada)
                && Objects.equals(dataSaida, reserva.dataSaida)
                && Objects.equals(dataCheckIn, reserva.dataCheckIn)
                && Objects.equals(dataCheckOut, reserva.dataCheckOut)
                && Objects.equals(dataCriacao, reserva.dataCriacao)
                && tipoQuarto == reserva.tipoQuarto
                && status == reserva.status
                && Objects.equals(observacoes, reserva.observacoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                nomeHospede,
                email,
                telefone,
                dataEntrada,
                dataSaida,
                dataCheckIn,
                dataCheckOut,
                dataCriacao,
                tipoQuarto,
                status,
                observacoes);
    }

    @Override
    public String toString() {
        return "reserva{" +
                "id=" + id +
                ", nomeHospede='" + nomeHospede + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dataEntrada=" + dataEntrada +
                ", dataSaida=" + dataSaida +
                ", dataCheckIn=" + dataCheckIn +
                ", dataCheckOut=" + dataCheckOut +
                ", dataCriacao=" + dataCriacao +
                ", tipoQuarto=" + tipoQuarto +
                ", status=" + status +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public void setNomeHospede(String nomeHospede) {
        this.nomeHospede = nomeHospede;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDateTime getDataCheckIn() {
        return dataCheckIn;
    }

    public void setDataCheckIn(LocalDateTime dataCheckIn) {
        this.dataCheckIn = dataCheckIn;
    }

    public LocalDateTime getDataCheckOut() {
        return dataCheckOut;
    }

    public void setDataCheckOut(LocalDateTime dataCheckOut) {
        this.dataCheckOut = dataCheckOut;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public TipoQuarto getTipoQuarto() {
        return tipoQuarto;
    }

    public void setTipoQuarto(TipoQuarto tipoQuarto) {
        this.tipoQuarto = tipoQuarto;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
