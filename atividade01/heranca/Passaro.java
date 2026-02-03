package atividade01.heranca;

public class Passaro extends Animal {
  @Override
  public void emitirSom() {
    System.out.println("Piu piu");
  }

  @Override
  public void dormir() {
    System.out.println("O pássaro está dormindo no ninho");
  }
}
