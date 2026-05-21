package com.utn.DentalOfficeManagement.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticaRequestDTO {

    @NotBlank(message = "El código interno no puede estar vacío")
    private String codigoInterno;

    @NotBlank(message = "El nombre de la práctica no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La descripción de la práctica no puede estar vacía")
    private String descripcion;

    @NotNull(message = "El precio base no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio base debe ser mayor o igual a 0")
    private BigDecimal precioBase;
}

