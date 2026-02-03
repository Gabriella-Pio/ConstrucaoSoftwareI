package atividade01.polimorfismo;

public class CalculadoraFrete {
  public static void main(String[] args) {
    Frete fretePAC = new FretePAC();
    Frete freteSEDEX = new FreteSEDEX();
    Frete freteRetirada = new FreteRetirada();

    double peso = 2.0; // peso em kg

    System.out.println("Frete PAC para " + peso + " kg: R$ " + fretePAC.calcular(peso));
    System.out.println("Frete SEDEX para " + peso + " kg: R$ " + freteSEDEX.calcular(peso));
    System.out.println("Frete Retirada para " + peso + " kg: R$ " + freteRetirada.calcular(peso));
  }
}
