package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class PracticaRealizadaRequestDTO {
    @NotNull(message = "La práctica es obligatoria")
    @Schema(description = "Identificador de la práctica", example = "10")
    private Long practicaId;

    @NotNull(message = "La pieza dental es obligatoria")
    @Schema(description = "Identificador de la pieza dental", example = "3")
    private Long piezaDentalId;

    @NotNull(message = "El nomenclador es obligatorio")
    @Schema(description = "Identificador del nomenclador de práctica", example = "4")
    private Long idNomencladorPractica;

    @NotNull(message = "La cobertura del plan es obligatoria")
    @Schema(description = "Identificador de la cobertura del plan", example = "5")
    private Long idCoberturaPlan;

    @NotNull(message = "El precio cobrado es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio cobrado debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2)
    @Schema(description = "Precio cobrado por la práctica", example = "1500.00")
    private BigDecimal precioCobrado;

    @Schema(description = "Identificador del pago asociado (opcional)", example = "7")
    private Long idPago;

    @NotNull(message = "El turno es obligatorio")
    @Schema(description = "Identificador del turno asociado", example = "9")
    private Long idTurno;
}
