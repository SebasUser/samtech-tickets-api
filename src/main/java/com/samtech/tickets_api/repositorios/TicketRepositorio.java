package com.samtech.tickets_api.repositorios;

import com.samtech.tickets_api.modelos.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepositorio extends JpaRepository<Ticket, Integer> {

    // Filtrar tickets por estado (Ingresado, En Diagnóstico, Reparado, Entregado)
    List<Ticket> findByEstado(String estado);

    // Ver historial de tickets de un equipo por su ID
    List<Ticket> findByEquipoIdEquipo(Integer idEquipo);

    // Ver historial de tickets buscando directo por el CODIGOTEK del equipo
    List<Ticket> findByEquipoCodigotek(String codigotek);
}