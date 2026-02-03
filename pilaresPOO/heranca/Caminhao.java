package pilaresPOO.heranca;

public class Caminhao extends Veiculo {

    public Caminhao(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public void information() {
        System.out.println("Marca: " + super.getMarca()); // Boa prática: usar super para indicar que o método vem da superclasse.
        System.out.println("Modelo: " + super.getModelo());
        System.out.println("Tipo: Caminhão");
    }
    
    public void carregar() {
      System.out.println("Caminhão carregado!");
    }

    public void descarregar() {
        System.out.println("Caminhão descarregado!");
    }
  
    // Polimorfismo: Sobrescrita do método ligar() para comportamento específico da Moto
    @Override
    public void ligar() {
      System.out.println("O caminhão está ligado!");
    }
}
