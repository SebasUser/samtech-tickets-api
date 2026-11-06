package com.samtech.tickets_api.controladores;

import com.samtech.tickets_api.modelos.Cliente;
import com.samtech.tickets_api.repositorios.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*") // Permite que Angular se conecte sin bloqueos de CORS
public class ClienteControlador {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    // 1. Obtener todos los clientes (GET)
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepositorio.findAll();
    }
    // 2. Obtener un cliente por su ID (GET)
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Integer id) {
        return clienteRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // 3. Registrar un nuevo cliente (POST)
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteRepositorio.save(cliente);
    }
    // 4. Actualizar datos de un cliente (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente detallesCliente) {
        return clienteRepositorio.findById(id)
                .map(cliente -> {
                    cliente.setNombreCompleto(detallesCliente.getNombreCompleto());
                    cliente.setTelefono(detallesCliente.getTelefono());
                    cliente.setEmail(detallesCliente.getEmail());
                    return ResponseEntity.ok(clienteRepositorio.save(cliente));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    // 5. Eliminar un cliente (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        if (clienteRepositorio.existsById(id)) {
            clienteRepositorio.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}