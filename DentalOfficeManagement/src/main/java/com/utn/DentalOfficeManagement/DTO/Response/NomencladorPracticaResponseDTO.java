package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "NomencladorPracticaResponseDTO", description = "Información del nomenclador de práctica")
public class NomencladorPracticaResponseDTO {
    @Schema(description = "Identificador del nomenclador", example = "4")
    private Long idNomencladorPractica;
    @Schema(description = "Identificador de la obra social", example = "2")
    private Long obraSocialId;
    @Schema(description = "Identificador de la práctica", example = "10")
    private Long practicaId;
    @Schema(description = "Código externo asociado a la práctica", example = "EXT-001")
    private String codigoExterno;
}

