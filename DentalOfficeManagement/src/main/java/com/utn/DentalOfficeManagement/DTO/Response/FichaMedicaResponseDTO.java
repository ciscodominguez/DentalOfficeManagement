package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "FichaMedicaResponseDTO", description = "Información médica asociada a un paciente")
public class FichaMedicaResponseDTO {
    @Schema(description = "Identificador de la ficha médica", example = "1")
    private Long idFichaMedica;
    @Schema(description = "Identificador del paciente asociado", example = "10")
    private Long pacienteId;
    @Schema(description = "Alergias declaradas por el paciente")
    private String alergias;
    @Schema(description = "Medicaciones actuales del paciente")
    private String medicacion;
    @Schema(description = "Antecedentes médicos relevantes")
    private String antecedentes;
    @Schema(description = "Grupo sanguíneo", example = "O+")
    private String grupoSanguineo;
}

