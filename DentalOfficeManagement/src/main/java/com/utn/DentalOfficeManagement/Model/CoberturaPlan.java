package com.utn.DentalOfficeManagement.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "cobertura_plan")
@Data
@NoArgsConstructor
public class CoberturaPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cobertura_plan")
    private Long idCoberturaPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_plan", nullable = false)
    private Plan plan;

    @Column(name = "porcentaje_cobertura", nullable = false, precision = 5, scale = 2)
    private BigDecimal porcentajeCobertura;

    @Column(name = "requiere_autorizacion", nullable = false)
    private Boolean requiereAutorizacion = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_practica", nullable = false)
    private Practica practica;
}