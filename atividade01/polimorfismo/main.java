package atividade01.polimorfismo;

public class main {
  public static void main(String[] args) {
    // Instancia fretes com taxas parametrizadas
    Frete fretePAC = new FretePAC(5);
    Frete freteSEDEX = new FreteSEDEX(10);
    Frete freteRetirada = new FreteRetirada(0);

    double peso = 2.0; // peso em kg

    System.out.println("Frete PAC para " + peso + " kg: R$ " + fretePAC.calcular(peso));
    System.out.println("Frete SEDEX para " + peso + " kg: R$ " + freteSEDEX.calcular(peso));
    System.out.println("Frete Retirada para " + peso + " kg: R$ " + freteRetirada.calcular(peso));
  }
}
