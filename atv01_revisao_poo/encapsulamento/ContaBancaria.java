package atividade01.encapsulamento;

public class ContaBancaria {
  private double saldo;

  // Constructor
  public ContaBancaria(double saldo) {
    this.saldo = saldo;
  }

  // Getters e Setters
  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  // Métodos
  public void depositar(double valor) {
    if (valor > 0) {
      saldo += valor;
    } else {
      System.out.println("Valor de depósito inválido.");
    }
  }

  public void sacar(double valor) {
    if (valor > 0 && valor <= saldo) {
      saldo -= valor;
    } else {
      System.out.println("Saldo insuficiente ou valor de saque inválido.");
    }
  }

  public void mostrarSaldo() {
    System.out.println("Saldo atual: " + saldo);
  }
}
