package com.samtech.tickets_api.modelos;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "equipos")
@Data
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_equipo")
    private Integer idEquipo;

    // Relación Muchos a Uno: Muchos equipos pueden pertenecer a un mismo Cliente
    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @Column(name = "codigotek", nullable = false, unique = true, length = 30)
    private String codigotek;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "marca_modelo", length = 100)
    private String marcaModelo;

    @Column(name = "numero_serie", length = 50)
    private String numeroSerie;

    @Column(name = "detalles_tecnicos", columnDefinition = "TEXT")
    private String detallesTecnicos;
}