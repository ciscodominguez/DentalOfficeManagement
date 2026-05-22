package com.utn.DentalOfficeManagement.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticaRequestDTO {

    @NotBlank(message = "El código interno no puede estar vacío")
    @Schema(description = "Código interno de la práctica", example = "PRAC-001")
    private String codigoInterno;

    @NotBlank(message = "El nombre de la práctica no puede estar vacío")
    @Schema(description = "Nombre de la práctica", example = "Limpieza dental")
    private String nombre;

    @NotBlank(message = "La descripción de la práctica no puede estar vacía")
    @Schema(description = "Descripción de la práctica")
    private String descripcion;

    @NotNull(message = "El precio base no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio base debe ser mayor o igual a 0")
    @Schema(description = "Precio base de la práctica", example = "1500.00")
    private BigDecimal precioBase;
}

