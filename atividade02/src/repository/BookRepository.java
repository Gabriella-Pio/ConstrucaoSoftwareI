package repository;

import java.util.List;
import java.util.Optional;

import entity.Book;

public interface BookRepository {

  /**
   * Saves a new book to the repository.
   * 
   * @param book the book to be saved
   * @return the saved book with an assigned ID
   */
  Book save(Book book);

  /**
   * Lists all books in the repository.
   * 
   * @return a list of all books
   */
  List<Book> listAll();

  /**
   * Finds a book by its ID.
   * 
   * @param id the ID of the book
   * @return an Optional containing the found book, or empty if not found
   */
  Optional<Book> findById(Integer id);

  /**
   * Finds a book by its ISBN.
   * 
   * @param isbn the ISBN of the book
   * @return an Optional containing the found book, or empty if not found
   */
  Optional<Book> findByIsbn(String isbn);

  /**
   * Updates an existing book in the repository.
   * 
   * @param book the book with updated information
   * @return true if the book was successfully updated, false otherwise
   */
  boolean update(Book book);

  /**
   * Deletes a book by its ID.
   * 
   * @param id the ID of the book to be deleted
   * @return true if the book was successfully deleted, false otherwise
   */
  boolean delete(Integer id);

  /**
   * Counts the total number of books in the repository.
   * 
   * @return the total number of books
   */
  int count();
}
