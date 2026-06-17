package com.utn.DentalOfficeManagement.DTO.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa los detalles de una pieza dental")
public class PiezaDentalResponseDTO {

    @Schema(description = "Identificador único de la pieza dental", example = "1")
    private Long idPiezaDental;

    @Schema(description = "Número de pieza dental (1-32)", example = "18")
    private Integer numeroPieza;

    @Schema(description = "Estado de la pieza dental", example = "CARIADO")
    private String estado;

    @Schema(description = "Observaciones sobre la pieza dental")
    private String observacion;
}