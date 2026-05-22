package com.utn.DentalOfficeManagement.DTO.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa los datos devueltos de un plan de salud asociado a una obra social")
public class PlanResponseDTO {

    @Schema(description = "Identificador del plan", example = "3")
    private Long idPlan;
    @Schema(description = "Identificador de la obra social asociada", example = "2")
    private Long obraSocialId;
    @Schema(description = "Nombre del plan", example = "Plan Básico")
    private String nombre;
    @Schema(description = "Descripción del plan", example = "Plan de salud básico con cobertura limitada")
    private String descripcion;
}

