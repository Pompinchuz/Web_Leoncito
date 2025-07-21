package com.leoncito.web.repository;

import com.leoncito.web.model.Cliente;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);
    boolean existsByDni(String dni);
    Optional<Cliente> findByEmail(String email);
}