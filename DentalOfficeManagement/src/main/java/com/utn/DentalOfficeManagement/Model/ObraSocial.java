package com.utn.DentalOfficeManagement.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "obra_social")
@Data
@NoArgsConstructor
public class ObraSocial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_obra_social")
    private Long idObraSocial;

    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;

    @Column(name = "cuit", nullable = false, length = 20)
    private String cuit;

    @Column(name = "telefono", length = 20)
    private String telefono;
}