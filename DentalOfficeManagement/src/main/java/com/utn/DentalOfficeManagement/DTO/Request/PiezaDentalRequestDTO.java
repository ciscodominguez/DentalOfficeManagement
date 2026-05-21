package com.utn.DentalOfficeManagement.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PiezaDentalRequestDTO {

    private Long idPiezaDental;

    @NotNull(message = "El número de pieza no puede ser nulo")
    @DecimalMin(value = "1.0", message = "El número de pieza debe ser mayor a 0")
    @DecimalMax(value = "32.0", message = "El número de pieza no puede ser mayor a 32")
    private BigDecimal numeroPieza;

    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    private String observacion;
}

