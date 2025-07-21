package com.leoncito.web.service;

import com.leoncito.web.model.Carrito;
import com.leoncito.web.model.Producto;
import com.leoncito.web.model.Usuario;
import com.leoncito.web.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Override
    public List<Carrito> obtenerCarritoUsuario(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Carrito agregarProducto(Long usuarioId, Long productoId, int cantidad) {
        // Buscar si ya existe el producto en el carrito
        Optional<Carrito> itemExistente = carritoRepository.findByUsuarioIdAndProductoId(usuarioId, productoId);
        
        if (itemExistente.isPresent()) {
            // Si existe, actualizar la cantidad
            Carrito carrito = itemExistente.get();
            carrito.setCantidad(carrito.getCantidad() + cantidad);
            return carritoRepository.save(carrito);
        } else {
            // Si no existe, crear nuevo item
            Carrito nuevoItem = new Carrito();
            
            // Configurar usuario (solo ID)
            Usuario usuario = new Usuario();
            usuario.setId(usuarioId);
            
            // Configurar producto (solo ID)
            Producto producto = new Producto();
            producto.setId(productoId);
            
            // Configurar el item del carrito
            nuevoItem.setUsuario(usuario);
            nuevoItem.setProducto(producto);
            nuevoItem.setCantidad(cantidad);
            
            return carritoRepository.save(nuevoItem);
        }
    }

    @Override
    public void eliminarProducto(Long usuarioId, Long productoId) {
        carritoRepository.deleteByUsuarioIdAndProductoId(usuarioId, productoId);
    }

    @Override
    public void vaciarCarrito(Long usuarioId) {
        carritoRepository.deleteByUsuarioId(usuarioId);
    }

    @Override
    public int contarProductosEnCarrito(Long usuarioId) {
        if(usuarioId == null) {
            return 0;
        }
        return carritoRepository.countByUsuarioId(usuarioId);
    }

    @Override
    public Double calcularTotalCarrito(Long usuarioId) {
        Double total = carritoRepository.sumTotalByUsuarioId(usuarioId);
        return total != null ? total : 0.0;
    }
}