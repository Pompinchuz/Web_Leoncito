package com.leoncito.web.dao;

import com.leoncito.web.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoDao {
    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);
}