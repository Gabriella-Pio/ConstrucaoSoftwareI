package pilaresPOO.heranca;

public class Moto extends Veiculo {

    public Moto(String marca, String modelo) {
        super(marca, modelo);
    }

    @Override
    public void information() {
        System.out.println("Marca: " + super.getMarca()); // Boa prática: usar super para indicar que o método vem da superclasse.
        System.out.println("Modelo: " + super.getModelo());
        System.out.println("Tipo: Moto");
    }
    
    public void empinar() {
      System.out.println("Moto empinando!");
    }

    public void buzinar() {
        System.out.println("Buzina tocando!");
    }  
}
