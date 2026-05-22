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
@Schema(name = "PagoRequestDTO", description = "Datos para crear o actualizar un pago")
public class PagoRequestDTO {

    @NotNull(message = "El saldo no puede ser nulo")
    @DecimalMin(value = "0.0", inclusive = true, message = "El saldo debe ser mayor o igual a 0")
    @Schema(description = "Saldo del pago", example = "1000.00")
    private BigDecimal saldo;

    @NotBlank(message = "El medio de pago no puede estar vacío")
    @Schema(description = "Medio de pago", example = "EFECTIVO")
    private String medio;
}

