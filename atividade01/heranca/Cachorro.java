package atividade01.heranca;

public class Cachorro extends Animal {
  @Override
  public void emitirSom() {
    System.out.println("Au au");
  }

  @Override
  public void comer() {
    System.out.println("O cachorro está comendo ração");
  }
}
