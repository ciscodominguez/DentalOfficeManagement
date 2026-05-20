package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FichaMedicaRequestDTO {

    @NotNull(message = "El paciente es obligatorio")
    private Long pacienteId;

    private String alergias;
    private String medicacion;
    private String antecedentes;
    private String grupoSanguineo;
}

