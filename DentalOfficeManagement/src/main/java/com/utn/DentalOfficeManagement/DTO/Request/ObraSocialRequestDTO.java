package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
public class ObraSocialRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre de la obra social", example = "OSDE")
    private String nombre;

    @NotBlank(message = "El CUIT es obligatorio")
    @Schema(description = "CUIT de la obra social", example = "30-12345678-9")
    private String cuit;

    @Schema(description = "Teléfono de la obra social", example = "+541112345678")
    private String telefono;
}

