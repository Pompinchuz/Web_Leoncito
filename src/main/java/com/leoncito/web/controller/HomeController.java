package com.leoncito.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String inicio() {
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
