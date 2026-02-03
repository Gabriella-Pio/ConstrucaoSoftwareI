package pilaresPOO.heranca;

public class Carro extends Veiculo {

    public Carro(String marca, String modelo) {
        super(marca, modelo);
    }

    // '@Override' indica que este método redefine o comportamento da classe Veiculo especificamente para Carro
    @Override
    public void information() {
        System.out.println("Marca: " + super.getMarca()); // Boa prática: usar super para indicar que o método vem da superclasse.
        System.out.println("Modelo: " + super.getModelo());
        System.out.println("Tipo: Carro");
    }
    
    // Polimorfismo: Sobrescrita do método ligar() para comportamento específico da Moto
    @Override
    public void ligar() {
      System.out.println("O carro está ligado!");
    }
}
