package com.leoncito.web.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {

    @GetMapping("/usuario/inicio")
    public String inicioUsuario() {
        return "usuario/inicio";
    }
}
