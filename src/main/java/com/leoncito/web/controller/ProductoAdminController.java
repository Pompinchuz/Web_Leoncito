package com.leoncito.web.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.leoncito.web.model.Producto;
import com.leoncito.web.repository.CategoriaRepository;
import com.leoncito.web.repository.ProductoRepository;

@Controller
@RequestMapping("/admin/productos")
@PreAuthorize("hasRole('ADMIN')")
public class ProductoAdminController {

    private final ProductoRepository productoRepo;

    private final CategoriaRepository categoriaRepo;
    public ProductoAdminController(ProductoRepository productoRepo, CategoriaRepository categoriaRepo) {
        this.productoRepo = productoRepo;
        this.categoriaRepo= categoriaRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoRepo.findAll());
        return "admin/productos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaRepo.findAll());

        return "admin/productos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoRepo.save(producto);
        return "redirect:/admin/productos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Producto producto = productoRepo.findById(id).orElseThrow();
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaRepo.findAll());

        return "admin/productos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        productoRepo.deleteById(id);
        return "redirect:/admin/productos";
    }
}
