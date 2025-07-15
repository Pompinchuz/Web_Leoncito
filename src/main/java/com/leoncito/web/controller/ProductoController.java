package com.leoncito.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.leoncito.web.service.ProductoService;
import com.leoncito.web.service.CategoriaService;

@Controller
public class ProductoController {
    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/productos")
    public String listarProductos(
        @RequestParam(name = "categoria", required = false) Long categoriaId,
        Model model) {
        
        model.addAttribute("categorias", categoriaService.listarTodas());
        
        if (categoriaId != null) {
            model.addAttribute("productos", productoService.buscarPorCategoria(categoriaId));
            model.addAttribute("categoriaSeleccionada", categoriaId);
        } else {
            model.addAttribute("productos", productoService.listar());
        }
        
        return "productos";
    }
}