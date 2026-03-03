package atividade01.polimorfismo;

public class FretePAC implements Frete {
  private double taxa;

  // Construtor padrão
  public FretePAC() {
    this.taxa = 5;
  }

  // Construtor parametrizado para maior flexibilidade
  public FretePAC(double taxa) {
    this.taxa = taxa;
  }

  @Override
  public double calcular(double peso) {
    return peso * taxa;
  }
}
