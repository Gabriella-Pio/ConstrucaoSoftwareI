package atividade01.polimorfismo;

public class FreteSEDEX implements Frete {
  private double taxa;

  // Construtor padrão
  public FreteSEDEX() {
    this.taxa = 10;
  }

  // Construtor parametrizado para maior flexibilidade
  public FreteSEDEX(double taxa) {
    this.taxa = taxa;
  }

  @Override
  public double calcular(double peso) {
    return peso * taxa;
  }
}
