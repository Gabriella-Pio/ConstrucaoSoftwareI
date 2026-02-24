package br.com.gabriella.biblioteca.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import br.com.gabriella.biblioteca.domain.Livro;

@Repository
public class LivroRepositoryMemoria implements LivroRepository {

    private static Integer contadorId = 1;
    private final Map<Integer, Livro> livros = new HashMap<>();

    @Override
    public Livro save(Livro livro) {
        if (livro == null) {
            throw new IllegalArgumentException("Não é possível salvar um livro nulo.");
        }

        if (livro.getId() == null) {
            livro.setId(contadorId++);
        }

        livros.put(livro.getId(), livro);
        return livro;
    }

    @Override
    public List<Livro> listAll() {
        return new ArrayList<>(livros.values());
    }

    @Override
    public Optional<Livro> findById(Integer id) {
        return Optional.ofNullable(livros.get(id));
    }

    @Override
    public Optional<Livro> findByIsbn(String isbn) {
        return livros.values().stream()
                .filter(l -> l.getIsbn().equals(isbn))
                .findFirst();
    }

    @Override
    public boolean update(Livro livro) {
        if (livro == null || !livros.containsKey(livro.getId())) {
            return false;
        }

        livros.put(livro.getId(), livro);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        if (livros.containsKey(id)) {
            livros.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return livros.size();
    }
}