package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
public class FichaMedicaRequestDTO {

    @NotNull(message = "El paciente es obligatorio")
    @Schema(description = "Identificador del paciente", example = "10")
    private Long pacienteId;

    @Schema(description = "Alergias del paciente")
    private String alergias;
    @Schema(description = "Medicaciones actuales del paciente")
    private String medicacion;
    @Schema(description = "Antecedentes médicos relevantes")
    private String antecedentes;
    @Schema(description = "Grupo sanguíneo", example = "O+")
    private String grupoSanguineo;
}

