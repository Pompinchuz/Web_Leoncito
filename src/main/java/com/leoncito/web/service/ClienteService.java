package com.leoncito.web.service;

import com.leoncito.web.model.Cliente;
import com.leoncito.web.model.Usuario;
import com.leoncito.web.repository.ClienteRepository;
import com.leoncito.web.repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Cliente registrarCliente(Cliente cliente, String password) {
        // Validaciones
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        if (clienteRepository.existsByDni(cliente.getDni())) {
            throw new RuntimeException("El DNI ya está registrado");
        }
        if (usuarioRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("El email ya está registrado como usuario");
        }
        
        // Crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(cliente.getNombre() + " " + cliente.getApellido());
        usuario.setEmail(cliente.getEmail());
        usuario.setPassword(password);
        
        // Establecer relación bidireccional
        cliente.setUsuario(usuario);
        usuario.setCliente(cliente); // ¡Esta línea es crucial!
        
        // Guardar (la cascada debería guardar ambos)
        return clienteRepository.save(cliente);
    }
    
    // Mantener los demás métodos igual
    public List<Cliente> listarTodosClientes() {
        return clienteRepository.findAll();
    }
    
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
    
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
    
    public Cliente buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }
}