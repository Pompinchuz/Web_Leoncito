package com.leoncito.web.service;


import com.leoncito.web.model.Usuario;
import java.util.List;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    void deleteById(Long id);
    Usuario findByEmail(String email);
    Usuario registrarUsuario(Usuario usuario);
}

