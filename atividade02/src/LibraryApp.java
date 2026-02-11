import java.util.Scanner;

import controller.BookController;
import service.BookService;

public class LibraryApp {
  public static void main(String[] args) {
    BookService bookService = new BookService();
    Scanner scanner = new Scanner(System.in);
    BookController bookController = new BookController(bookService, scanner);
    bookController.showMenu();
  }
}
