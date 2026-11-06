package com.samtech.tickets_api.modelos;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private Integer idTicket;

    @ManyToOne
    @JoinColumn(name = "id_equipo", nullable = false)
    private Equipo equipo;

    @ManyToOne
    @JoinColumn(name = "id_tecnico")
    private Tecnico tecnico;

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Column(name = "problema_reportado", columnDefinition = "TEXT")
    private String problemaReportado;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "diagnostico_final", columnDefinition = "TEXT")
    private String diagnosticoFinal;

    @Column(name = "solucion_aplicada", columnDefinition = "TEXT")
    private String solucionAplicada;

    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;

    // Asigna fecha actual y estado por defecto automáticamente al crear un ticket
    @PrePersist
    public void prePersist() {
        if (this.fechaIngreso == null) {
            this.fechaIngreso = LocalDateTime.now();
        }
        if (this.estado == null || this.estado.isEmpty()) {
            this.estado = "Ingresado";
        }
    }
}