package com.leoncito.web.controller;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.leoncito.web.model.Usuario;
import com.leoncito.web.repository.UsuarioRepository;

@Controller
public class RegisterController {

    private final UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UsuarioRepository usuarioRepo, PasswordEncoder passwordEncoder) {
        this.usuarioRepo = usuarioRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registro")
    public String formularioRegistro() {
        return "register";
    }

    @PostMapping("/registro")
    public String registrarNuevoUsuario(@RequestParam String username,
                                        @RequestParam String password) {
        if (usuarioRepo.findByUsername(username).isPresent()) {
            // Ya existe ese usuario (puedes redirigir con mensaje de error)
            return "redirect:/registro?error";
        }

        Usuario nuevo = new Usuario();
        nuevo.setUsername(username);
        nuevo.setPassword(passwordEncoder.encode(password));
        nuevo.setRol("USER");

        usuarioRepo.save(nuevo);
        return "redirect:/login?registroExitoso";
    }
}
