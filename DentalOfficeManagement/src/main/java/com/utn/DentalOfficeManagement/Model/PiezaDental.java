package com.utn.DentalOfficeManagement.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pieza_dental")
@Data
@NoArgsConstructor
public class PiezaDental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // ← agregar esto
    @Column(name = "id_pieza_dental")
    private Long idPiezaDental;

    @Column(name = "numero_pieza", nullable = false, precision = 5, scale = 2)
    private Integer numeroPieza; // Número de pieza dental (1-32)

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paciente")
    private Paciente paciente;
}
