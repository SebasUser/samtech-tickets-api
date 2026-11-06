package com.samtech.tickets_api.modelos;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clientes")
@Data// Lombok crea automáticamente los getters, setters y constructores
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 100)
    private String email;
}