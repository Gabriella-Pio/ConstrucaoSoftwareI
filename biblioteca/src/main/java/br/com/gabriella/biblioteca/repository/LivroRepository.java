package br.com.gabriella.biblioteca.repository;

import java.util.List;
import java.util.Optional;
import br.com.gabriella.biblioteca.domain.Livro;

public interface LivroRepository {
    Livro save(Livro livro);
    List<Livro> listAll();
    Optional<Livro> findById(Integer id);
    Optional<Livro> findByIsbn(String isbn);
    boolean update(Livro livro);
    boolean delete(Integer id);
    int count();
}