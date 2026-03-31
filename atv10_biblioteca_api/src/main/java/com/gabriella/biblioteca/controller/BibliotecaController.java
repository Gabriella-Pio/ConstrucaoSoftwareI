// package com.gabriella.biblioteca.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import com.gabriella.biblioteca.entity.Autor;
// import com.gabriella.biblioteca.entity.Livro;
// import com.gabriella.biblioteca.service.BibliotecaService;
// import java.util.List;
// import java.util.UUID;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// @RestController
// @RequestMapping("/api/biblioteca")
// public class BibliotecaController {
  
//   @Autowired
//   private BibliotecaService bibliotecaService;

//   @PostMapping("/livros")
//   public ResponseEntity<Livro> cadastrarLivro(@RequestBody BibliotecaService request) {
//     Livro livro = bibliotecaService.cadastrarLivro(request.getTitulo(), request.getAnoPublicacao(), request.getAutores());
//     return ResponseEntity.status(HttpStatus.CREATED).body(livro);
//   }

//   @PostMapping("/livros/{idLivro}/autores/{idAutor}")
//   public ResponseEntity<Void> addAutor(@PathVariable UUID idLivro, @PathVariable UUID idAutor) {
//     bibliotecaService.addAutor(idLivro, idAutor);
//     return ResponseEntity.ok().build();
//   }


// }
