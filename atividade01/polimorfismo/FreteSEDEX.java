package atividade01.polimorfismo;

public class FreteSEDEX implements Frete {
  @Override
  public double calcular(double peso) {
    return peso * 10;
  }
}
