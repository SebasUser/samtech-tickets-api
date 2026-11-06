package com.samtech.tickets_api.controladores;

import com.samtech.tickets_api.modelos.Tecnico;
import com.samtech.tickets_api.repositorios.TecnicoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
@CrossOrigin(origins = "*")
public class TecnicoControlador {

    @Autowired
    private TecnicoRepositorio tecnicoRepositorio;

    @GetMapping
    public List<Tecnico> listarTecnicos() {
        return tecnicoRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> obtenerTecnicoPorId(@PathVariable Integer id) {
        return tecnicoRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tecnico crearTecnico(@RequestBody Tecnico tecnico) {
        return tecnicoRepositorio.save(tecnico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tecnico> actualizarTecnico(@PathVariable Integer id, @RequestBody Tecnico detalles) {
        return tecnicoRepositorio.findById(id)
                .map(tecnico -> {
                    tecnico.setNombre(detalles.getNombre());
                    tecnico.setEmail(detalles.getEmail());
                    return ResponseEntity.ok(tecnicoRepositorio.save(tecnico));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTecnico(@PathVariable Integer id) {
        if (tecnicoRepositorio.existsById(id)) {
            tecnicoRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}