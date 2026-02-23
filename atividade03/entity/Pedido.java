package atividade03.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Pedido {

    private static int contadorId = 1;

    private int id;
    private String cliente;
    private String descricao;
    private double valor;
    private StatusPedido status;
    private LocalDateTime dataCriacao;

    // Construtor com validações
    public Pedido(String cliente, String descricao, double valor) {
        this.id = contadorId++;
        setCliente(cliente);
        setDescricao(descricao);
        setValor(valor);
        this.status = StatusPedido.CRIADO;
        this.dataCriacao = LocalDateTime.now();
    }

    // Getters e setters com validação
    // Setters
    public void setCliente(String cliente) {
        if (cliente == null || cliente.trim().length() < 3) {
            throw new IllegalArgumentException("Cliente deve ter ao menos 3 caracteres.");
        }
        this.cliente = cliente;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().length() < 5) {
            throw new IllegalArgumentException("Descrição deve ter ao menos 5 caracteres.");
        }
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

    // Não pode ser alterado se estiver CANCELADO
    // Não pode marcar como ENVIADO se não estiver PAGO
    public void setStatus(StatusPedido novoStatus) {
        if (this.status == StatusPedido.CANCELADO) {
            throw new IllegalArgumentException("Não pode ser alterado");
        }
        this.status = novoStatus;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    // equals() e hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pedido pedido = (Pedido) o;

        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString()
    public String toString() {
        return "Pedido {" +
                "id = " + id + '\'' +
                ", cliente = " + cliente + '\'' +
                ", descrição = " + descricao + '\'' +
                ", valor = " + valor + '\'' +
                ", status = " + status + '\'' +
                ", data de criação = " + dataCriacao +
                '}';
    }
}