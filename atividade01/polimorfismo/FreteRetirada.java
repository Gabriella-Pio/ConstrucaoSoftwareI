package atividade01.polimorfismo;

public class FreteRetirada implements Frete {
  private double taxa;

  // Construtor padrão
  public FreteRetirada() {
    this.taxa = 0;
  }

  // Construtor parametrizado para maior flexibilidade
  public FreteRetirada(double taxa) {
    this.taxa = taxa;
  }

  @Override
  public double calcular(double peso) {
    return peso * taxa;
  }
}
