package com.utn.DentalOfficeManagement.DTO.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa los datos base de una práctica o procedimiento odontológico del catálogo")
public class PracticaResponseDTO {

    @Schema(description = "Identificador único de la práctica", example = "12")
    private Long idPractica;

    @Schema(description = "Código de identificación interno de la clínica para la práctica", example = "PRAC-042")
    private String codigoInterno;

    @Schema(description = "Nombre comercial o clínico del procedimiento", example = "Extracción Dental Simple")
    private String nombre;

    @Schema(description = "Descripción detallada del alcance de la práctica",
            example = "Exodoncia de pieza dental monorradicular o multirradicular sin complicaciones que requieran cirugía.")
    private String descripcion;

    @Schema(description = "Precio base o de lista establecido para la práctica sin coberturas aplicadas", example = "25000.00")
    private BigDecimal precioBase;

}

