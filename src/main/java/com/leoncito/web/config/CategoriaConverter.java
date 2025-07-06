package com.leoncito.web.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.leoncito.web.model.Categoria;
import com.leoncito.web.repository.CategoriaRepository;

@Component
public class CategoriaConverter implements Converter<String, Categoria> {

    private final CategoriaRepository categoriaRepo;

    public CategoriaConverter(CategoriaRepository categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    @Override
    public Categoria convert(String source) {
        try {
            Long id = Long.parseLong(source);
            return categoriaRepo.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
