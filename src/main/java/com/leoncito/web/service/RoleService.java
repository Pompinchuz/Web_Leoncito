package com.leoncito.web.service;

import com.leoncito.web.model.Role;
import java.util.List;

public interface RoleService {
    List<Role> findAll();
    Role findByNombre(String nombre);
    Role save(Role role);
    void deleteById(Long id);
}
