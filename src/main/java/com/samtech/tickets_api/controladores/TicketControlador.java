package com.samtech.tickets_api.controladores;

import com.samtech.tickets_api.modelos.Ticket;
import com.samtech.tickets_api.repositorios.TicketRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*")
public class TicketControlador {

    @Autowired
    private TicketRepositorio ticketRepositorio;

    // 1. Listar todos los tickets
    @GetMapping
    public List<Ticket> listarTickets() {
        return ticketRepositorio.findAll();
    }

    // 2. Obtener un ticket por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerTicketPorId(@PathVariable Integer id) {
        return ticketRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Filtrar tickets por estado (Ej: /api/tickets/estado/Ingresado)
    @GetMapping("/estado/{estado}")
    public List<Ticket> listarPorEstado(@PathVariable String estado) {
        return ticketRepositorio.findByEstado(estado);
    }

    // 4. Ver historial de un equipo por su CODIGOTEK (Ej: /api/tickets/codigotek/2609TK0503243)
    @GetMapping("/codigotek/{codigotek}")
    public List<Ticket> listarPorCodigotek(@PathVariable String codigotek) {
        return ticketRepositorio.findByEquipoCodigotek(codigotek);
    }

    // 5. Crear un nuevo ticket (POST)
    @PostMapping
    public Ticket crearTicket(@RequestBody Ticket ticket) {
        return ticketRepositorio.save(ticket);
    }

    // 6. Actualizar ticket: cambiar estado, agregar diagnóstico final o precio (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Ticket> actualizarTicket(@PathVariable Integer id, @RequestBody Ticket detalles) {
        return ticketRepositorio.findById(id)
                .map(ticket -> {
                    if (detalles.getEquipo() != null) ticket.setEquipo(detalles.getEquipo());
                    if (detalles.getTecnico() != null) ticket.setTecnico(detalles.getTecnico());
                    if (detalles.getProblemaReportado() != null) ticket.setProblemaReportado(detalles.getProblemaReportado());
                    if (detalles.getEstado() != null) ticket.setEstado(detalles.getEstado());
                    ticket.setDiagnosticoFinal(detalles.getDiagnosticoFinal());
                    ticket.setSolucionAplicada(detalles.getSolucionAplicada()); // <-- AGREGAR ESTA LÍNEA
                    ticket.setPrecio(detalles.getPrecio());
                    return ResponseEntity.ok(ticketRepositorio.save(ticket));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 7. Eliminar un ticket (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTicket(@PathVariable Integer id) {
        if (ticketRepositorio.existsById(id)) {
            ticketRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}