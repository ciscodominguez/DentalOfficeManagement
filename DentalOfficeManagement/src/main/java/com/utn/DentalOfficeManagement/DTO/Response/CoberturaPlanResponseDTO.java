package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CoberturaPlanResponseDTO", description = "Detalle de la cobertura de una práctica dentro de un plan")
public class CoberturaPlanResponseDTO {
    @Schema(description = "Identificador de la cobertura", example = "5")
    private Long idCoberturaPlan;
    @Schema(description = "Identificador del plan asociado", example = "2")
    private Long planId;
    @Schema(description = "Identificador de la práctica", example = "10")
    private Long practicaId;
    @Schema(description = "Porcentaje de cobertura", example = "80.00")
    private BigDecimal porcentajeCobertura;
    @Schema(description = "Indica si requiere autorización")
    private Boolean requiereAutorizacion;
}

