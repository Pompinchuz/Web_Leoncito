// src/main/java/com/leoncito/web/service/CarritoService.java
package com.leoncito.web.service;

import com.leoncito.web.model.Carrito;
import java.util.List;

public interface CarritoService {
    List<Carrito> obtenerCarritoUsuario(Long usuarioId);
    Carrito agregarProducto(Long usuarioId, Long productoId, int cantidad);
    void eliminarProducto(Long usuarioId, Long productoId);
    void vaciarCarrito(Long usuarioId);
    int contarProductosEnCarrito(Long usuarioId);
    Double calcularTotalCarrito(Long usuarioId);
}