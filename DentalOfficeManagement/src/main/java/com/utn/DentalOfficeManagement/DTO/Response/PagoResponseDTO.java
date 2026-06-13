package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PagoResponseDTO", description = "Información de un pago")
public class PagoResponseDTO {

    @Schema(description = "Identificador del pago", example = "7")
    private Long idPago;
    @Schema(description = "Saldo del pago", example = "1000.00")
    private BigDecimal saldo;
    @Schema(description = "Medio de pago utilizado", example = "EFECTIVO")
    private String medio;

    @Schema(description = "Fecha del pago", example = "2024-06-15")
    private LocalDate fechaPago;
}

