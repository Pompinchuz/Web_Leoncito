package com.leoncito.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.leoncito.web.service.ProductoService;

@Controller
public class HomeController {

    private final ProductoService productoService;

    // Inyección de dependencias por constructor
    public HomeController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/")
    public String inicio(Model model) {
        model.addAttribute("productos", productoService.listar());
        return "index";  
    }
    
    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros"; 
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";  
    }
}