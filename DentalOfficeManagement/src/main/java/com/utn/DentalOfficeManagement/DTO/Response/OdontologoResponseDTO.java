package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OdontologoResponseDTO {
    private Integer idOdontologo;
    private Integer usuarioId;
    private String nombre;
    private String matricula;
    private String especialidad;
}

