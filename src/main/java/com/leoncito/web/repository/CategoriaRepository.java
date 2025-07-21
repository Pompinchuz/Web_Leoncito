package com.leoncito.web.repository;

import com.leoncito.web.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    // Métodos básicos ya incluidos por JpaRepository
}