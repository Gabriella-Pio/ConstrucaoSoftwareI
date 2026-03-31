package com.gabriella.biblioteca.repository;

import com.gabriella.biblioteca.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
