package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticaResponseDTO {

    private Long idPractica;
    private String codigoInterno;
    private String nombre;
    private String descripcion;
    private BigDecimal precioBase;
}

