package com.utn.DentalOfficeManagement.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nomenclador_practica")
@Data
@NoArgsConstructor
public class NomencladorPractica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nomenclador_practica")
    private Integer idNomencladorPractica;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_obra_social", nullable = false)
    private ObraSocial obraSocial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "practica_id", nullable = false)
    private Practica practica;

    @Column(name = "codigo_externo", nullable = false, length = 50)
    private String codigoExterno;
}