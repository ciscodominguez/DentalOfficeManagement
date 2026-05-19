package com.utn.DentalOfficeManagement.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticaRequestDTO {

    private String codigoInterno;
    private String nombre;
    private String descripcion;
    private BigDecimal precioBase;
}

