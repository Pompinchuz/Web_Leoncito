package com.leoncito.web.service;

import com.leoncito.web.model.Role;
import com.leoncito.web.model.Usuario;
import com.leoncito.web.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private RoleService roleService;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            Role userRole = roleService.findByNombre("USER");
            usuario.setRoles(Collections.singletonList(userRole));
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        return this.save(usuario); // Guarda sin encriptar
    }
}