package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NomencladorPracticaRequestDTO {

    @NotNull(message = "La obra social es obligatoria")
    private Long obraSocialId;

    @NotNull(message = "La práctica es obligatoria")
    private Long practicaId;

    @NotBlank(message = "El código externo es obligatorio")
    private String codigoExterno;
}

