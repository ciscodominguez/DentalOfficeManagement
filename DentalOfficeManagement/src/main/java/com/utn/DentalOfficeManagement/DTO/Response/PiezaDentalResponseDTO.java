package com.utn.DentalOfficeManagement.DTO.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa los detalles de una pieza dental específica")
public class PiezaDentalResponseDTO {

    @Schema(description = "Identificador único de la pieza dental en la base de datos", example = "18")
    private Long idPiezaDental;

    @Schema(description = "Número de la pieza dental según el sistema internacional (ej. 11 para el incisivo central superior derecho)",
            example = "11")
    private Integer numero;

    @Schema(description = "Nombre o tipo de la pieza dental", example = "Incisivo Central")
    private String nombre;

    @Schema(description = "Sector o cuadrante de la boca donde se ubica la pieza",
            example = "Superior Derecho")
    private String posicion;
}
