package atividade01.heranca;

public class Animais {
  public static void main(String[] args) {
    Animal gato = new Gato();
    Animal cachorro = new Cachorro();
    Animal passaro = new Passaro();

    gato.emitirSom();
    cachorro.emitirSom();
    passaro.emitirSom();
    gato.comer();
    cachorro.dormir();
    passaro.dormir();
  }
}
