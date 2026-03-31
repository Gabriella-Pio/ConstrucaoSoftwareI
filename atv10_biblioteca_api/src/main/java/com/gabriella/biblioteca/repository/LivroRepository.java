package com.gabriella.biblioteca.repository;

import com.gabriella.biblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

}
