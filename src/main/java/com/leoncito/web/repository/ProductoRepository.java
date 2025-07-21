package com.leoncito.web.repository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leoncito.web.model.Producto;

import java.util.List;
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @EntityGraph(attributePaths = {"categoria"})
    List<Producto> findAll();
    
    
    @Query("SELECT p FROM Producto p JOIN FETCH p.categoria")
    List<Producto> findAllWithCategoria();
    
    List<Producto> findByCategoriaId(Long categoriaId);
    
    


}
