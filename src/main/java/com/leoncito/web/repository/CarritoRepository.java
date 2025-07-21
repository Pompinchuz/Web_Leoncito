package com.leoncito.web.repository;

import com.leoncito.web.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {

    // Buscar todos los items del carrito de un usuario
    List<Carrito> findByUsuarioId(Long usuarioId);
    
    // Buscar un item específico por usuario y producto
    @Query("SELECT c FROM Carrito c WHERE c.usuario.id = ?1 AND c.producto.id = ?2")
    Optional<Carrito> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);
    
    // Eliminar un item específico del carrito
    @Transactional
    @Modifying
    @Query("DELETE FROM Carrito c WHERE c.usuario.id = ?1 AND c.producto.id = ?2")
    void deleteByUsuarioIdAndProductoId(Long usuarioId, Long productoId);
    
    // Vaciar todo el carrito de un usuario
    @Transactional
    @Modifying
    @Query("DELETE FROM Carrito c WHERE c.usuario.id = ?1")
    void deleteByUsuarioId(Long usuarioId);
    
    // Contar items en el carrito de un usuario
    @Query("SELECT COUNT(c) FROM Carrito c WHERE c.usuario.id = ?1")
    int countByUsuarioId(Long usuarioId);
    
    // Calcular el total del carrito
    @Query("SELECT SUM(p.precio * c.cantidad) FROM Carrito c JOIN c.producto p WHERE c.usuario.id = ?1")
    Double sumTotalByUsuarioId(Long usuarioId);
}