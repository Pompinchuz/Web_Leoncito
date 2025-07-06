package com.leoncito.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leoncito.web.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsername(String username);
}
