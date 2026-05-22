package com.utn.DentalOfficeManagement.Model;

import com.utn.DentalOfficeManagement.Model.*;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "practica_realizada")
@Data
@NoArgsConstructor
public class PracticaRealizada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_practica_realizada")
    private Long idPracticaRealizada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "practica_id", nullable = false)
    private Practica practica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pieza_dental_id", nullable = false)
    private PiezaDental piezaDental;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nomenclador_practica", nullable = false)
    private NomencladorPractica nomencladorPractica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cobertura_plan", nullable = false)
    private CoberturaPlan coberturaPlan;

    @Column(name = "precio_cobrado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCobrado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pago")
    private Pago pago; // nullable: relación 0-1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_turno", nullable = false)
    private Turno turno;
}