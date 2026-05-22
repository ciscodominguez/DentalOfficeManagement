package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
public class NomencladorPracticaRequestDTO {

    @NotNull(message = "La obra social es obligatoria")
    @Schema(description = "Identificador de la obra social", example = "2")
    private Long obraSocialId;

    @NotNull(message = "La práctica es obligatoria")
    @Schema(description = "Identificador de la práctica", example = "10")
    private Long practicaId;

    @NotBlank(message = "El código externo es obligatorio")
    @Schema(description = "Código externo de la práctica", example = "EXT-001")
    private String codigoExterno;
}

