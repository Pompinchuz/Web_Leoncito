package com.leoncito.web.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leoncito.web.model.Producto;

import java.util.List;
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria_NombreIgnoreCase(String nombre);
   
}

