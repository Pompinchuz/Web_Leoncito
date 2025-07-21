package com.leoncito.web.dao;

import com.leoncito.web.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioDao {
    List<Usuario> findAll();
    Optional<Usuario> findById(Long id);
    Usuario save(Usuario usuario);
    void deleteById(Long id);
    Optional<Usuario> findByEmail(String email);
}
