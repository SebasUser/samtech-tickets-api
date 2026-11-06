package com.samtech.tickets_api.controladores;

import com.samtech.tickets_api.modelos.Equipo;
import com.samtech.tickets_api.repositorios.EquipoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@CrossOrigin(origins = "*")
public class EquipoControlador {

    @Autowired
    private EquipoRepositorio equipoRepositorio;

    // 1. Listar todos los equipos
    @GetMapping
    public List<Equipo> listarEquipos() {
        return equipoRepositorio.findAll();
    }

    // 2. Obtener un equipo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable Integer id) {
        return equipoRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Buscar un equipo por su CODIGOTEK (Ej: /api/equipos/codigo/2609TK0503243)
    @GetMapping("/codigo/{codigotek}")
    public ResponseEntity<Equipo> obtenerPorCodigotek(@PathVariable String codigotek) {
        return equipoRepositorio.findByCodigotek(codigotek)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Listar equipos de un cliente específico (Ej: /api/equipos/cliente/1)
    @GetMapping("/cliente/{idCliente}")
    public List<Equipo> listarPorCliente(@PathVariable Integer idCliente) {
        return equipoRepositorio.findByClienteIdCliente(idCliente);
    }

    // 5. Registrar un nuevo equipo (POST)
    @PostMapping
    public Equipo crearEquipo(@RequestBody Equipo equipo) {
        return equipoRepositorio.save(equipo);
    }

    // 6. Actualizar un equipo (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable Integer id, @RequestBody Equipo detallesEquipo) {
        return equipoRepositorio.findById(id)
                .map(equipo -> {
                    equipo.setCliente(detallesEquipo.getCliente());
                    equipo.setCodigotek(detallesEquipo.getCodigotek());
                    equipo.setTipo(detallesEquipo.getTipo());
                    equipo.setMarcaModelo(detallesEquipo.getMarcaModelo());
                    equipo.setNumeroSerie(detallesEquipo.getNumeroSerie());
                    equipo.setDetallesTecnicos(detallesEquipo.getDetallesTecnicos());
                    return ResponseEntity.ok(equipoRepositorio.save(equipo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // 7. Eliminar un equipo (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable Integer id) {
        if (equipoRepositorio.existsById(id)) {
            equipoRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}