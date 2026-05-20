package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FichaMedicaResponseDTO {
    private Integer idFichaMedica;
    private Integer pacienteId;
    private String alergias;
    private String medicacion;
    private String antecedentes;
    private String grupoSanguineo;
}

