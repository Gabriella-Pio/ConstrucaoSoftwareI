package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import entity.Book;
import entity.Funcionario;

public class BookRepositoryMemory implements BookRepository {

  private final Map<Integer, Book> books = new HashMap<>();

  @Override
  public Book save(Book book) {
    // Validate book
    if (book == null) {
      throw new IllegalArgumentException("Book cannot be null");
    }

    // Check for duplicate ISBN
    Optional<Book> existingBook = findByIsbn(book.getIsbn());
    if (existingBook.isPresent()) {
      throw new IllegalArgumentException("A book with the same ISBN already exists");
    }

    // Save book
    books.put(book.getId(), book);
    return book;
  }

  @Override
  public List<Book> listAll() {
    return new ArrayList<>(books.values());
  }

  @Override
  public Optional<Book> findById(Integer id) {
    // return Optional.ofNullable(books.get(id));

    return books.values().stream()
        .filter(book -> book.getId().equals(id))
        .findFirst();
  }

  @Override
  public Optional<Book> findByIsbn(String isbn) {
    return books.values().stream()
        .filter(book -> book.getIsbn().equals(isbn))
        .findFirst();
  }

  @Override
  public boolean update(Book book) {

    if (book == null || !books.containsKey(book.getId())) {
      return false;
    }

    for (int i = 0; i < books.size(); i++) {

      if (book == null || !books.containsKey(book.getId())) {
        return false;
      }

      // books.put(book.getId(), book);
      // return true;

      Book existing = books.get(i);
      
      if (existing.getId().equals(book.getId())) {
        // Check for ISBN conflict
        Optional<Book> byIsbn = findByIsbn(book.getIsbn());
        if (byIsbn.isPresent() && !byIsbn.get().getId().equals(book.getId())) {
          throw new IllegalArgumentException("A book with the same ISBN already exists");
        }

        // Update book
        books.put(book.getId(), book);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean delete(Integer id) {
    if (books.containsKey(id)) {
      books.remove(id);
      return true;
    }
    return false;
  }

  @Override
  public int count() {
    return books.size(); 
  }
}