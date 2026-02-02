package pilaresPOO;

import java.util.Scanner;

public class books {
    // Atributos Privados: Esconde os dados para que não sejam acessados ou alterados diretamente de fora da classe.
    private String title;
    private String author;
    private String ISBN;
    private int publicationYear;

    // Constructor
    public books(String title, String author, String ISBN, int publicationYear) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        // Uso do setter no constructor: garante que o objeto já nasça obedecendo às regras de validação.
        setPublicationYear(publicationYear);
    }

    // Getters e Setters: Fornecem acesso controlado e seguro aos atributos privados.
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    // O Setter atua como um filtro (proteção) para manter a integridade do objeto.
    public void setPublicationYear(int publicationYear) {
      if( publicationYear >= 0) {
        this.publicationYear = publicationYear;
      } else {
        throw new IllegalArgumentException("Publication year cannot be negative.");
      }
    }

    // Method to display book information
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Publication Year: " + publicationYear);
    }

    // Method to add a review
    public void addReview(String review) {
        System.out.println("Review added: " + review);
    }
}
