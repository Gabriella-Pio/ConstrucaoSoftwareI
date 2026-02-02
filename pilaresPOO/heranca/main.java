package pilaresPOO.heranca;

public class main {
    public static void main(String[] args) {
        // Instanciando Carro
        Carro meuCarro = new Carro("Toyota", "Corolla");
        meuCarro.information();
        meuCarro.ligar();
        meuCarro.desligar();

        System.out.println("---------------------");

        // Instanciando Moto
        Moto minhaMoto = new Moto("Honda", "CB500F");
        minhaMoto.information();
        minhaMoto.empinar();
        minhaMoto.buzinar();

        System.out.println("---------------------");

        // Instanciando Caminhão
        Caminhao meuCaminhao = new Caminhao("Volvo", "FH16");
        meuCaminhao.information();
        meuCaminhao.carregar();
        meuCaminhao.descarregar();
    }
  
}
