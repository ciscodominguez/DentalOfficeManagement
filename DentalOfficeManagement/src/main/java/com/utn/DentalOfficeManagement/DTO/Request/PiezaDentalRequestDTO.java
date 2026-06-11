package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PiezaDentalRequestDTO {

    @Schema(description = "Identificador de la pieza dental", example = "1")
    private Long idPiezaDental;

    @NotNull(message = "El número de pieza no puede ser nulo")
    @Min(value = 1, message = "El número de pieza debe ser mayor a 0")
    @Max(value = 32, message = "El número de pieza no puede ser mayor a 32")
    @Schema(description = "Número de pieza dental (1-32)", example = "12")
    private Integer numeroPieza;

    @NotBlank(message = "El estado no puede estar vacío")
    @Schema(description = "Estado de la pieza dental", example = "SANO")
    private String estado;

    @Schema(description = "Observaciones sobre la pieza dental")
    private String observacion;
}

