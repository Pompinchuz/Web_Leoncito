package com.leoncito.web.dao;

import com.leoncito.web.model.Carrito;
import java.util.List;
import java.util.Optional;

public interface CarritoDao {
    List<Carrito> findByUsuarioId(Long usuarioId);
    Carrito save(Carrito carrito);
    void deleteById(Long id);
    void deleteByUsuarioId(Long usuarioId);
    Optional<Carrito> findByUsuarioIdAndProductoId(Long usuarioId, Long productoId);
}