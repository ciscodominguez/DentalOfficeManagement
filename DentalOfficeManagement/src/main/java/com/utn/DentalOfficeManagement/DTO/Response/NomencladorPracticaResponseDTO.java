package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NomencladorPracticaResponseDTO {
    private Integer idNomencladorPractica;
    private Integer obraSocialId;
    private Integer practicaId;
    private String codigoExterno;
}

