package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
public class OdontologoRequestDTO {

    @NotNull(message = "El usuario es obligatorio")
    @Schema(description = "Identificador del usuario asociado", example = "4")
    private Long usuarioId;

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre del odontólogo", example = "María Gómez")
    private String nombre;

    @NotBlank(message = "La matrícula es obligatoria")
    @Schema(description = "Número de matrícula profesional", example = "MAT1234")
    private String matricula;

    @Schema(description = "Especialidad del odontólogo", example = "Ortodoncia")
    private String especialidad;
}

