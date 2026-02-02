package pilaresPOO.heranca;

public class Veiculo {
    // Atributos privados que serão herdados por meio de métodos públicos (Getters/Setters)
    private String marca;
    private String modelo;

    public Veiculo(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    // Método base que pode ser sobrescrito pelas subclasses para fornecer detalhes específicos
    public void information() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'information'");
    } 
}