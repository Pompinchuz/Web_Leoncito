package com.leoncito.web.controller;

import com.leoncito.web.model.Usuario;
import com.leoncito.web.security.CustomUserDetails;
import com.leoncito.web.service.CarritoService;
import com.leoncito.web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private UsuarioService usuarioService;

    private Long obtenerUsuarioId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = usuarioService.findByEmail(email);
        return usuario.getId();
    }

    @GetMapping
    public String verCarrito(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !(auth.getPrincipal() instanceof String)) {
            CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
            Long usuarioId = userDetails.getId();
            model.addAttribute("carrito", carritoService.obtenerCarritoUsuario(usuarioId));
            model.addAttribute("total", carritoService.calcularTotalCarrito(usuarioId));
        }
        return "carrito";
    }

    @PostMapping("/agregar")
    public String agregarProducto(@RequestParam Long productoId, @RequestParam int cantidad) {
        Long usuarioId = obtenerUsuarioId();
        carritoService.agregarProducto(usuarioId, productoId, cantidad);
        return "redirect:/carrito";
    }

    @PostMapping("/eliminar")
    public String eliminarProducto(@RequestParam Long productoId) {
        Long usuarioId = obtenerUsuarioId();
        carritoService.eliminarProducto(usuarioId, productoId);
        return "redirect:/carrito";
    }

    @PostMapping("/vaciar")
    public String vaciarCarrito() {
        Long usuarioId = obtenerUsuarioId();
        carritoService.vaciarCarrito(usuarioId);
        return "redirect:/carrito";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        Long usuarioId = obtenerUsuarioId();
        model.addAttribute("carrito", carritoService.obtenerCarritoUsuario(usuarioId));
        model.addAttribute("total", carritoService.calcularTotalCarrito(usuarioId));
        return "checkout";
    }

    @PostMapping("/confirmar")
public String confirmarCompra(RedirectAttributes redirectAttributes) {
    try {
        Long usuarioId = obtenerUsuarioId();
        
        // 1. Vaciar el carrito (usando tu método existente)
        carritoService.vaciarCarrito(usuarioId);
        
        // 2. Mostrar mensaje de éxito
        redirectAttributes.addFlashAttribute("mensaje", "¡Compra realizada con éxito!");
        
        // 3. Redirigir a confirmación
        return "redirect:/carrito/confirmacion";
    } catch (Exception e) {
        redirectAttributes.addFlashAttribute("error", "Error al confirmar: " + e.getMessage());
        return "redirect:/carrito/checkout";
    }
}

@GetMapping("/confirmacion")
public String mostrarConfirmacion() {
    return "confirmacion-compra";
}




}