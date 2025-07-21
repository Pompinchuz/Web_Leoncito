package com.leoncito.web.dao;

import com.leoncito.web.model.Usuario;
import com.leoncito.web.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioDaoImpl implements UsuarioDao {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }
    
    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }
    
    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }
    
    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
} 