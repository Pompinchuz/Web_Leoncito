package com.leoncito.web.controller;

import com.leoncito.web.model.Cliente;
import com.leoncito.web.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;
    
    // Mostrar formulario de registro
    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        if (!model.containsAttribute("cliente")) {
            model.addAttribute("cliente", new Cliente());
        }
        return "registro-cliente";
    }
    
    // Procesar registro
    @PostMapping("/registrar")
    public String registrarCliente(
            @ModelAttribute Cliente cliente, 
            @RequestParam String password,
            RedirectAttributes redirectAttributes) {
        
        try {
            Cliente clienteRegistrado = clienteService.registrarCliente(cliente, password);
            redirectAttributes.addFlashAttribute("mensaje", 
                "Cliente registrado con éxito. ID Usuario: " + 
                clienteRegistrado.getUsuario().getId());
            return "redirect:/clientes/exito";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("cliente", cliente);
            return "redirect:/clientes/registro";
        }
    }
    
    // Página de éxito
    @GetMapping("/exito")
    public String mostrarPaginaExito() {
        return "registro-exitoso";
    }
    
    // Listar clientes
    @GetMapping("/listar")
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listarTodosClientes());
        return "lista-clientes";
    }
    
    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", cliente);
        
        // Agregar información del usuario asociado
        if (cliente.getUsuario() != null) {
            model.addAttribute("usuarioEmail", cliente.getUsuario().getEmail());
        }
        return "editar-cliente";
    }
    
    // Actualizar cliente
    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(
            @PathVariable Long id,
            @ModelAttribute Cliente cliente,
            RedirectAttributes redirectAttributes) {
        
        try {
            // Asegurar que el ID se mantenga
            cliente.setId(id);
            clienteService.actualizarCliente(cliente);
            redirectAttributes.addFlashAttribute("mensaje", "Cliente actualizado con éxito");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/clientes/listar";
    }
    
    // Eliminar cliente
    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id, 
                                RedirectAttributes redirectAttributes) {
        try {
            clienteService.eliminarCliente(id);
            redirectAttributes.addFlashAttribute("mensaje", 
                "Cliente y su usuario asociado eliminados con éxito");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", 
                "Error al eliminar: " + e.getMessage());
        }
        return "redirect:/clientes/listar";
    }
    
    // Ver detalles del cliente
    @GetMapping("/ver/{id}")
    public String verDetallesCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        model.addAttribute("cliente", cliente);
        return "detalle-cliente";
    }
}