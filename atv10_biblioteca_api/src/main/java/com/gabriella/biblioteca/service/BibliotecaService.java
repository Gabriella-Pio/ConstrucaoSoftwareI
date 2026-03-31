package com.gabriella.biblioteca.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.gabriella.biblioteca.entity.Autor;
import com.gabriella.biblioteca.entity.Livro;
import com.gabriella.biblioteca.repository.AutorRepository;
import com.gabriella.biblioteca.repository.LivroRepository;

@Service
public class BibliotecaService {

  private final LivroRepository livroRepository;
  private final AutorRepository autorRepository;

  public BibliotecaService(LivroRepository livroRepository, AutorRepository autorRepository) {
    this.livroRepository = livroRepository;
    this.autorRepository = autorRepository;
  }

  // Cadastrar livro
  public Livro cadastrarLivro(String titulo, Integer anoPublicacao, List<UUID> autor) {
    Livro livro = new Livro(titulo, anoPublicacao);
    List<Autor> autores = this.autorRepository.findAllById(autor);
    autores.forEach(livro::addAutor);
    return this.livroRepository.save(livro);
  }

  // Adicionar autor a um livro
  public void addAutor(UUID idLivro, UUID idAutor) {
    Livro livro = livroRepository.findById(idLivro)
        .orElseThrow();
    Autor autor = autorRepository.findById(idAutor)
        .orElseThrow();

    livro.addAutor(autor);
    livroRepository.save(livro);
  }
}
