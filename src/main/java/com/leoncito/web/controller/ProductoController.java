package com.leoncito.web.controller;

import com.leoncito.web.service.CategoriaService;
import com.leoncito.web.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    @Autowired
    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public String listarProductos(
            @RequestParam(value = "categoria", required = false) Long categoriaId,
            Model model) {
        
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("categoriaSeleccionada", categoriaId);
        
        if (categoriaId != null) {
            model.addAttribute("productos", productoService.findByCategoriaId(categoriaId));
        } else {
            model.addAttribute("productos", productoService.findAll());
        }
        
        return "productos";
    }
}