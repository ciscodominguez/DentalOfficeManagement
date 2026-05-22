package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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
    @DecimalMin(value = "0.0", inclusive = true, message = "El porcentaje debe ser mayor o igual a 0")
    @DecimalMax(value = "100.0", inclusive = true, message = "El porcentaje no puede ser mayor a 100")
    private BigDecimal porcentajeCobertura;

    private Boolean requiereAutorizacion = false;
}

