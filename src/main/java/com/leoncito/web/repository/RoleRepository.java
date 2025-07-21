package com.leoncito.web.repository;

import com.leoncito.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNombre(String nombre);
}
