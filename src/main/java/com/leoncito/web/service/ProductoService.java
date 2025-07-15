package com.leoncito.web.service;

import com.leoncito.web.model.Producto;
import com.leoncito.web.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    
    private final ProductoRepository productoRepository;

    // Inyección por constructor (mejor práctica que @Autowired)
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listar() {
    return productoRepository.findAllWithCategoria(); // Usa el método optimizado
}
    
    // Puedes agregar estos métodos adicionales si los necesitas:
    public List<Producto> listarDestacados() {
        return productoRepository.findAll(); // Ejemplo: los 3 más caros
    }
    
    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    // Método para filtrar por categoría
    public List<Producto> buscarPorCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    

}