package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObraSocialResponseDTO {
    private Integer idObraSocial;
    private String nombre;
    private String cuit;
    private String telefono;
}

