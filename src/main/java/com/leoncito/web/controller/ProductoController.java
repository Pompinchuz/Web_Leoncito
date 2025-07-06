package com.leoncito.web.controller;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leoncito.web.repository.CategoriaRepository;
import com.leoncito.web.repository.ProductoRepository;
import com.leoncito.web.service.ProductoService;
@Controller
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    private ProductoRepository productoRepo;
    
     public ProductoController(ProductoRepository productoRepo) {
        this.productoRepo = productoRepo;
        
    }

    @GetMapping("/productos")
    public String verProductos(Model model) {
        model.addAttribute("productos", productoRepo.findAll());
        return "productos";
    }


 
}
