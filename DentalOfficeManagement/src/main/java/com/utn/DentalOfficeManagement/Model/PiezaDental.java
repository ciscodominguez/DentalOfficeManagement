package com.utn.DentalOfficeManagement.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;

@Entity
@Table(name = "pieza_dental")
@Data
@NoArgsConstructor
public class PiezaDental {

    @Id
    @Column(name = "id_pieza_dental", unique = true)
    private Long idPiezaDental; // No auto-generado: número de pieza es fijo (1-32)

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
