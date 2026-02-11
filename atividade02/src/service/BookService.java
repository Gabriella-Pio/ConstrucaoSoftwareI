package service;

import repository.BookRepository;

import java.util.List;
import java.util.Optional;

import entity.Book;

public class BookService {

  private final BookRepository repository;

  public BookService(BookRepository repository) {
    this.repository = repository;
  }

  // Métodos com regras de negócio:

  // - cadastrarLivro()
  /**
   * Registers a new book in the system.
   * 
   * @param title           The title of the book.
   * @param author          The author of the book.
   * @param isbn            The ISBN of the book.
   * @param publicationYear The publication year of the book.
   * @return The registered book.
   */
  public Book signUpBook(String title, String author, String isbn, Integer publicationYear) {
    String cleanIsbn = isbn != null ? isbn.replaceAll("\\D", "") : null;

    Book book = new Book(title, author, cleanIsbn, publicationYear, true);
    return repository.save(book);
  }

  // - listarLivros()
  /**
   * Lists all books in the system.
   * 
   * @return A list of all books.
   */
  public List<Book> listBooks() {
    List<Book> books = repository.listAll();

    if (books.isEmpty()) {
      System.out.println("No books found in the system.");
    }

    return books;
  }

  // - buscarLivroPorId()
  /**
   * Finds a book by its ID.
   * 
   * @param id The ID of the book.
   * @return The book with the specified ID, or null if not found.
   */
  public Book findBookById(Integer id) {
    Optional<Book> books = repository.findById(id);

    if (books.isEmpty()) {
      System.out.println("No book found with ID: " + id);
      return null;
    }

    return books.get();
  }

  // - atualizarLivro()
  /**
   * Updates an existing book in the system.
   * 
   * @param book The book with updated information.
   * @return true if the book was successfully updated.
   */
  public boolean updateBook(Book book) {
    if (book == null)
      return false;
    return repository.update(book);
  }

  // - removerLivro()
  /**
   * Deletes a book by its ID.
   * 
   * @param id The ID of the book to be deleted.
   * @return true if the book was successfully deleted.
   */
  public boolean deleteBook(Integer id) {
    if (repository.findById(id).isEmpty()) {
      System.out.println("No book found with ID: " + id);
      return false;
    }

    return repository.delete(id);
  }

  // - marcarComoIndisponivel()
  /**
   * Marks a book as unavailable.
   * 
   * @param id The ID of the book to be marked as unavailable.
   * @return true if the book was successfully marked as unavailable.
   */
  public boolean markAsUnavailable(Integer id) {
    Optional<Book> optionalBook = repository.findById(id);

    if (optionalBook.isEmpty()) {
      System.out.println("No book found with ID: " + id);
      return false;
    }

    Book book = optionalBook.get();
    if (!book.getAvailable()) {
      System.out.println("Book with ID: " + id + " is already marked as unavailable.");
      return false;
    }

    book.setAvailable(false);
    return repository.update(book);
  }

  // - marcarComoDisponivel()
  /**
   * Marks a book as available.
   * 
   * @param id The ID of the book to be marked as available.
   * @return true if the book was successfully marked as available.
   */
  public boolean markAsAvailable(Integer id) {
    Optional<Book> optionalBook = repository.findById(id);
    if (optionalBook.isEmpty()) {
      System.out.println("No book found with ID: " + id);
      return false;
    }

    Book book = optionalBook.get();
    if (book.getAvailable()) {
      System.out.println("Book with ID: " + id + " is already marked as available.");
      return false;
    }

    book.setAvailable(true);
    return repository.update(book);
  }
}
