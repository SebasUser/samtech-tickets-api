package com.samtech.tickets_api.modelos;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tecnicos")
@Data
public class Tecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tecnico")
    private Integer idTecnico;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", length = 100)
    private String email;
}