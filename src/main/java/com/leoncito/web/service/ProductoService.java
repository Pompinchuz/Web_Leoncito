package com.leoncito.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leoncito.web.model.Producto;
import com.leoncito.web.repository.ProductoRepository;

import java.util.List;
@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listar() {
        return productoRepository.findAll();
    }
}
