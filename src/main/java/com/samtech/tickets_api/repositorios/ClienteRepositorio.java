package com.samtech.tickets_api.repositorios;

import com.samtech.tickets_api.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
    // Al extender de JpaRepository, ya tienes listos los métodos save(), findAll(), findById() y deleteById()
}
