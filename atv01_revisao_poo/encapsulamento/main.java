package atividade01.encapsulamento;

public class main {
  public static void main(String[] args) {
    ContaBancaria conta = new ContaBancaria(1000.0);
    conta.mostrarSaldo();
    conta.depositar(500.0);
    conta.mostrarSaldo();
    conta.sacar(200.0);
    conta.mostrarSaldo();
  }
}