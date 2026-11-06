package com.samtech.tickets_api.repositorios;

import com.samtech.tickets_api.modelos.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipoRepositorio extends JpaRepository<Equipo, Integer> {

    // 1. Busca un equipo exacto por su código interno (ej. 2609TK0503243)
    Optional<Equipo> findByCodigotek(String codigotek);

    // 2. Lista todos los equipos que pertenecen a un cliente específico
    List<Equipo> findByClienteIdCliente(Integer idCliente);
}


