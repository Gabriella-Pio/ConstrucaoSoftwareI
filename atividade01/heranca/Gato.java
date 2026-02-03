package atividade01.heranca;

public class Gato extends Animal {
  @Override
  public void emitirSom() {
    System.out.println("Miau");
  }

  @Override
  public void comer() {
    System.out.println("O gato está comendo atum");
  }

  @Override
  public void dormir() {
    System.out.println("O gato está dormindo em uma caixa");
  }
}
