package atividade01.polimorfismo;

public class FretePAC implements Frete {
  @Override
  public double calcular(double peso) {
    return peso * 5;
  }
}
