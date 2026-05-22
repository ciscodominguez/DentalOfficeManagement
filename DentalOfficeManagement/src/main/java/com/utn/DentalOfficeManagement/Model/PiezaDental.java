package com.utn.DentalOfficeManagement.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Entity
@Table(name = "pieza_dental")
@Data
@NoArgsConstructor
public class PiezaDental {

    @Id
    @Column(name = "id_pieza_dental", unique = true)
    private Long idPiezaDental; // No auto-generado: número de pieza es fijo (1-32)

    @Column(name = "numero_pieza", nullable = false, precision = 5, scale = 2)
    private BigDecimal numeroPieza;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "observacion", columnDefinition = "TEXT")
    private String observacion;
}
