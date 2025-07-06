package com.leoncito.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoncito.web.model.Categoria;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombreIgnoreCase(String nombre);
}