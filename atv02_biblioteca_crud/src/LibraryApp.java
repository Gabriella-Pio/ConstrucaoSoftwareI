import java.util.Scanner;

import controller.BookController;
import service.BookService;
import repository.BookRepository;
import repository.BookRepositoryMemory;

public class LibraryApp {
  public static void main(String[] args) {
    BookRepositoryMemory repository = new BookRepositoryMemory();

    BookService bookService = new BookService(repository);

    Scanner scanner = new Scanner(System.in);

    BookController bookController = new BookController(bookService, scanner);

    bookController.showMenu();

    scanner.close();
  }
}
