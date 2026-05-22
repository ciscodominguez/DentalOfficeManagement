package com.utn.DentalOfficeManagement.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PiezaDentalRequestDTO {

    @Schema(description = "Identificador de la pieza dental", example = "1")
    private Long idPiezaDental;

    @NotNull(message = "El número de pieza no puede ser nulo")
    @DecimalMin(value = "1.0", message = "El número de pieza debe ser mayor a 0")
    @DecimalMax(value = "32.0", message = "El número de pieza no puede ser mayor a 32")
    @Schema(description = "Número de pieza dental (1-32)", example = "12")
    private BigDecimal numeroPieza;

    @NotBlank(message = "El estado no puede estar vacío")
    @Schema(description = "Estado de la pieza dental", example = "SANO")
    private String estado;

    @Schema(description = "Observaciones sobre la pieza dental")
    private String observacion;
}

