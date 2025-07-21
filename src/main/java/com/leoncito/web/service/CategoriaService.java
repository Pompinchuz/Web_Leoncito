package com.leoncito.web.service;

import com.leoncito.web.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    List<Categoria> findAll();
    Optional<Categoria> findById(Long id);
    Categoria save(Categoria categoria);
}