package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PracticaRealizadaRequestDTO {
    @NotNull(message = "La práctica es obligatoria")
    private Long practicaId;

    @NotNull(message = "La pieza dental es obligatoria")
    private Long piezaDentalId;

    @NotNull(message = "El nomenclador es obligatorio")
    private Long idNomencladorPractica;

    @NotNull(message = "La cobertura del plan es obligatoria")
    private Long idCoberturaPlan;

    @NotNull(message = "El precio cobrado es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio cobrado debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal precioCobrado;

    private Long idPago;

    @NotNull(message = "El turno es obligatorio")
    private Long idTurno;
}
