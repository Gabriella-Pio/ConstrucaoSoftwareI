package entity;

import java.util.Objects;

public class Book {

  // Contador para id com auto-incremento
  private static int counterId = 1;

  // Atributes
  private Integer id;
  private String title;
  private String author;
  private String isbn;
  private Integer publicationYear;
  private Boolean available = true; // Padrão true

  // Constructor
  public Book(String title, String author, String isbn, Integer publicationYear, Boolean available) {
    this.id = counterId++;
    setTitle(title);
    setAuthor(author);
    setIsbn(isbn);
    setPublicationYear(publicationYear);
    setAvailable(available);
  }

  // Setters
  // 1. Título:
  // Não pode ser nulo/vazio
  // Mínimo 3 caracteres após trim()
  public void setTitle(String title) {
    if (title == null || title.trim().length() < 3) {
      throw new IllegalArgumentException("O título do livro deve conter pelo menos 3 caracteres.");
    }
    this.title = title;
  }

  // 2. Autor:
  // Não pode ser nulo/vazio
  // Mínimo 3 caracteres após trim()
  public void setAuthor(String author) {
    if (author == null || author.trim().length() < 3) {
      throw new IllegalArgumentException("O autor do livro deve conter pelo menos 3 caracteres.");
    }
    this.author = author;
  }

  // 3. ISBN:
  // Valida se tem 13 dígitos
  // Formato: XXX-X-XXXX-XXXX-X é adicionado pelo método MaskIsbn()
  // Deve ser único no sistema
  public void setIsbn(String isbn) {

    // Remove tudo que não for dígito antes de validar
    String cleanIsbn = (isbn == null) ? "" : isbn.replaceAll("\\D", "");

    if (cleanIsbn.length() != 13) {
      throw new IllegalArgumentException("O ISBN deve conter exatamente 13 dígitos numéricos.");
    }
    this.isbn = cleanIsbn;
  }

  // 4. Ano:
  // Entre 1500 e 2025
  public void setPublicationYear(Integer publicationYear) {
    if (publicationYear == null || publicationYear < 1500 || publicationYear > 2025) {
      throw new IllegalArgumentException("O ano de publicação deve estar entre 1500 e 2025.");
    }
    this.publicationYear = publicationYear;
  }

  // - disponivel (boolean, padrão true)
  public void setAvailable(Boolean available) {
    if (available == null) {
      throw new IllegalArgumentException("O campo disponível não pode ser nulo.");
    }
    this.available = available;
  }

  // Getters
  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getIsbn() {
    return isbn;
  }

  public Integer getPublicationYear() {
    return publicationYear;
  }

  public Boolean getAvailable() {
    return available;
  }

  // Métodos de negócio

  public String getIsbnFormatted() {
    if (this.isbn == null)
      return null;
    return maskIsbn(this.isbn);
  }

  // Recebe o ISBN (13 dígitos) e retorna uma versão mascarada
  // XXX-X-XXXX-XXXX-X
  private String maskIsbn(String isbn) {
    if (isbn == null || isbn.length() != 13) {
      throw new IllegalStateException("O ISBN deve conter exatamente 13 dígitos para ser mascarado.");
    }
    return String.format("%s-%s-%s-%s-%s",
        isbn.substring(0, 3),
        isbn.substring(3, 4),
        isbn.substring(4, 8),
        isbn.substring(8, 12),
        isbn.substring(12));
  }

  // Equals e HashCode (baseados no ISBN, que deve ser único)
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Book book = (Book) o;

    return Objects.equals(isbn, book.isbn);
  }

  @Override
  public int hashCode() {
    return Objects.hash(isbn);
  }

  // toString
  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", isbn='" + isbn + '\'' +
        ", publicationYear=" + publicationYear +
        ", available=" + available +
        '}';
  }
}
