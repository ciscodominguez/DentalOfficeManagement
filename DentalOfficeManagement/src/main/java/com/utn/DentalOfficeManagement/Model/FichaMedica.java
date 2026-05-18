package com.utn.DentalOfficeManagement.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ficha_medica")
@Data
@NoArgsConstructor
public class FichaMedica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ficha_medica")
    private Integer idFichaMedica;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false, unique = true)
    private Paciente paciente;

    @Column(name = "alergias", columnDefinition = "TEXT")
    private String alergias;

    @Column(name = "medicacion", columnDefinition = "TEXT")
    private String medicacion;

    @Column(name = "antecedentes", columnDefinition = "TEXT")
    private String antecedentes;

    @Column(name = "grupo_sanguineo", length = 5)
    private String grupoSanguineo;
}