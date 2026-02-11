package controller;

import service.BookService;
import java.util.Scanner;

public class BookController {

  private final BookService bookService;
  private final Scanner scanner;

  public BookController(BookService bookService, Scanner scanner) {
    this.bookService = bookService;
    this.scanner = scanner;
  }

  // Menu interativo
  public void showMenu() {
    int option = -1;
    while (option != 0) {
      System.out.println("=== Sistema de Gerenciamento de Livros ===");
      System.out.println("1. Cadastrar Livro");
      System.out.println("2. Listar Livros");
      System.out.println("3. Buscar Livro por ID");
      System.out.println("4. Marcar Livro como Indisponível");
      System.out.println("5. Marcar Livro como Disponível");
      System.out.println("6. Atualizar Livro");
      System.out.println("7. Deletar Livro");
      System.out.println("0. Sair");
      System.out.print("Escolha uma opção: ");
      option = Integer.parseInt(scanner.nextLine());

      switch (option) {
        case 1:
          signUpBook();
          break;
        case 2:
          listBooks();
          break;
        case 3:
          findBookById();
          break;
        case 4:
          markAsUnavailable();
          break;
        case 5:
          updateBook();
          break;
        case 6:
          deleteBook();
          break;
        case 0:
          System.out.println("Saindo do sistema...");
          break;
        default:
          System.out.println("Opção inválida. Tente novamente.");
      }
    }
  }

  // Métodos para cada funcionalidade
  public void signUpBook() {
    System.out.println("=== Cadastrar Livro ===");
    System.out.print("Título: ");
    String title = scanner.nextLine();
    System.out.print("Autor: ");
    String author = scanner.nextLine();
    System.out.print("ISBN: ");
    String isbn = scanner.nextLine();
    System.out.print("Ano de Publicação: ");
    Integer publicationYear = Integer.parseInt(scanner.nextLine());

    try {
      bookService.signUpBook(title, author, isbn, publicationYear);
      System.out.println("Livro cadastrado com sucesso!");
    } catch (IllegalArgumentException e) {
      System.err.println("Erro ao cadastrar livro: " + e.getMessage());
    }
  }

  public void listBooks() {
    System.out.println("=== Listar Livros ===");
    var books = bookService.listBooks();
    if (books.isEmpty()) {
      System.out.println("Nenhum livro cadastrado.");
    } else {
      books.forEach(book -> {
        System.out.println("-----------------------------");
        System.out.println("ID: " + book.getId());
        System.out.println("Título: " + book.getTitle());
        System.out.println("Autor: " + book.getAuthor());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Ano de Publicação: " + book.getPublicationYear());
        System.out.println("Disponível: " + (book.getAvailable() ? "Sim" : "Não"));
        System.out.println("-----------------------------");
      });
    }
  }

  public void findBookById() {
    System.out.println("=== Buscar Livro por ID ===");
    System.out.print("ID do livro: ");
    Integer id = Integer.parseInt(scanner.nextLine());

    var book = bookService.findBookById(id);
    if (book != null) {
      System.out.println("ID: " + book.getId());
      System.out.println("Título: " + book.getTitle());
      System.out.println("Autor: " + book.getAuthor());
      System.out.println("ISBN: " + book.getIsbn());
      System.out.println("Ano de Publicação: " + book.getPublicationYear());
      System.out.println("Disponível: " + (book.getAvailable() ? "Sim" : "Não"));
    } else {
      System.out.println("Livro com ID " + id + " não encontrado.");
    }
  }

  public void markAsUnavailable() {
    System.out.println("=== Marcar Livro como Indisponível ===");
    System.out.print("ID do livro: ");
    Integer id = Integer.parseInt(scanner.nextLine());

    try {
      boolean success = bookService.markAsUnavailable(id);
      if (success) {
        System.out.println("Livro marcado como indisponível com sucesso!");
      } else {
        System.out.println("Livro com ID " + id + " não encontrado.");
      }
    } catch (IllegalArgumentException e) {
      System.err.println("Erro ao marcar livro como indisponível: " + e.getMessage());
    }
  }

  public void markAsAvailable() {
    System.out.println("=== Marcar Livro como Disponível ===");
    System.out.print("ID do livro: ");
    Integer id = Integer.parseInt(scanner.nextLine());

    try {
      boolean success = bookService.markAsAvailable(id);
      if (success) {
        System.out.println("Livro marcado como disponível com sucesso!");
      } else {
        System.out.println("Livro com ID " + id + " não encontrado.");
      }
    } catch (IllegalArgumentException e) {
      System.err.println("Erro ao marcar livro como disponível: " + e.getMessage());
    }
  }

  public void updateBook() {
    System.out.println("=== Atualizar Livro ===");
    System.out.print("ID do livro a ser atualizado: ");
    Integer id = Integer.parseInt(scanner.nextLine());

    var existingBook = bookService.findBookById(id);
    if (existingBook == null) {
      System.out.println("Livro com ID " + id + " não encontrado.");
      return;
    }

    System.out.print("Novo título: ");
    String title = scanner.nextLine();
    System.out.print("Novo autor: ");
    String author = scanner.nextLine();
    System.out.print("Novo ISBN: ");
    String isbn = scanner.nextLine();
    System.out.print("Novo ano de publicação: ");
    Integer publicationYear = Integer.parseInt(scanner.nextLine());

    try {
      existingBook.setTitle(title);
      existingBook.setAuthor(author);
      existingBook.setIsbn(isbn);
      existingBook.setPublicationYear(publicationYear);

      boolean success = bookService.updateBook(existingBook);
      if (success) {
        System.out.println("Livro atualizado com sucesso!");
      } else {
        System.out.println("Erro ao atualizar livro.");
      }
    } catch (IllegalArgumentException e) {
      System.err.println("Erro ao atualizar livro: " + e.getMessage());
    }
  }

  public void deleteBook() {
    System.out.println("=== Deletar Livro ===");
    System.out.print("ID do livro a ser deletado: ");
    Integer id = Integer.parseInt(scanner.nextLine());

    boolean success = bookService.deleteBook(id);
    if (success) {
      System.out.println("Livro deletado com sucesso!");
    } else {
      System.out.println("Livro com ID " + id + " não encontrado.");
    }
  }

  // Tratamento de erros


  // Métodos auxiliares de leitura de dados


}
