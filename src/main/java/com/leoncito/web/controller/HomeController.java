package com.leoncito.web.controller;

import com.leoncito.web.security.CustomUserDetails;
import com.leoncito.web.service.CarritoService;
import com.leoncito.web.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication; // ¡Cambiado!
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CarritoService carritoService;
    
    @GetMapping({"/", "/home"})
    public String home(Model model, Authentication authentication) {
        model.addAttribute("productos", productoService.findAll());
        
        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            int cantidad = carritoService.contarProductosEnCarrito(userDetails.getId());
            model.addAttribute("carritoCount", cantidad);
        } else {
            model.addAttribute("carritoCount", 0);
        }
        
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