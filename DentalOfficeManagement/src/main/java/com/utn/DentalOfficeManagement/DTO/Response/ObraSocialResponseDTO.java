package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ObraSocialResponseDTO", description = "Datos de una obra social")
public class ObraSocialResponseDTO {
    @Schema(description = "Identificador de la obra social", example = "2")
    private Long idObraSocial;
    @Schema(description = "Nombre de la obra social", example = "OSDE")
    private String nombre;
    @Schema(description = "CUIT de la obra social", example = "30-12345678-9")
    private String cuit;
    @Schema(description = "Teléfono de la obra social", example = "+541112345678")
    private String telefono;
}

