package com.utn.DentalOfficeManagement.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequestDTO {

    @NotNull(message = "El id de obra social no puede ser nulo")
    @Schema(description = "Identificador de la obra social", example = "2")
    private Long obraSocialId;

    @NotBlank(message = "El nombre del plan no puede estar vacío")
    @Schema(description = "Nombre del plan", example = "Plan Básico")
    private String nombre;

    @NotBlank(message = "La descripción del plan no puede estar vacía")
    @Schema(description = "Descripción del plan")
    private String descripcion;
}

