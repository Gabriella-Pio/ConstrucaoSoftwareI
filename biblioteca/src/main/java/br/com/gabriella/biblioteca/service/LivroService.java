package br.com.gabriella.biblioteca.service;

import br.com.gabriella.biblioteca.domain.Livro;
import br.com.gabriella.biblioteca.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    private final LivroRepository repository;

    // Injeção via construtor (Obrigatório pela Parte 5)
    public LivroService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro cadastrarLivro(Livro livro) {
        // Regra obrigatória: Validar ISBN único
        if (repository.findByIsbn(livro.getIsbn()).isPresent()) {
            throw new IllegalArgumentException("Já existe um livro cadastrado com este ISBN.");
        }

        // Delegar persistência ao repository
        return repository.save(livro);
    }

    public List<Livro> listarLivros() {
        return repository.listAll();
    }

    public Livro buscarLivroPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado com o ID: " + id));
    }

    public void atualizarLivro(Integer id, Livro livroAtualizado) {
        Livro livroExistente = buscarLivroPorId(id);

        // Regra: Não permitir alterar ISBN (mantemos o original)
        livroAtualizado.setId(id);
        livroAtualizado.setIsbn(livroExistente.getIsbn());

        repository.update(livroAtualizado);
    }

    public void removerLivro(Integer id) {
        // Regra: Lançar exceção se não existir
        if (!repository.delete(id)) {
            throw new RuntimeException("Não foi possível remover: Livro não encontrado.");
        }
    }

    public void marcarComoIndisponivel(Integer id) {
        Livro livro = buscarLivroPorId(id);
        livro.marcarComoIndisponivel();
        repository.update(livro);
    }

    public void marcarComoDisponivel(Integer id) {
        Livro livro = buscarLivroPorId(id);
        livro.marcarComoDisponivel();
        repository.update(livro);
    }
}