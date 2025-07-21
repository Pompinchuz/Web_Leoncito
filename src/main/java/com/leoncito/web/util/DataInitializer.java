package com.leoncito.web.util;

import com.leoncito.web.model.Role;
import com.leoncito.web.model.Usuario;
import com.leoncito.web.service.RoleService;
import com.leoncito.web.service.UsuarioService;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {
    
    private final UsuarioService usuarioService;
    private final RoleService roleService;
    
    public DataInitializer(UsuarioService usuarioService, RoleService roleService) {
        this.usuarioService = usuarioService;
        this.roleService = roleService;
    }
    
    @Override
    @Transactional
    public void run(String... args) {
        // Crear roles
        Role adminRole = createRoleIfNotExists("ADMIN");
        Role userRole = createRoleIfNotExists("USER");
        
        // Crear admin (contraseña sin encriptar)
        if (usuarioService.findByEmail("admin@leoncito.com") == null) {
            Usuario admin = new Usuario();
            admin.setNombre("Admin");
            admin.setEmail("admin@leoncito.com");
            admin.setPassword("admin123"); // Sin encriptar
            admin.setRoles(List.of(adminRole));
            usuarioService.save(admin);
        }
    }
    
    private Role createRoleIfNotExists(String nombre) {
        Role role = roleService.findByNombre(nombre);
        if (role == null) {
            role = new Role();
            role.setNombre(nombre);
            roleService.save(role);
        }
        return role;
    }
}