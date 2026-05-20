package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CoberturaPlanRequestDTO {

    @NotNull(message = "El plan es obligatorio")
    private Long planId;

    @NotNull(message = "La práctica es obligatoria")
    private Long practicaId;

    @NotNull(message = "El porcentaje de cobertura es obligatorio")
    private BigDecimal porcentajeCobertura;

    private Boolean requiereAutorizacion = false;
}

