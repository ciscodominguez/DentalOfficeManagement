package com.utn.DentalOfficeManagement.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PiezaDentalRequestDTO {

    private Integer idPiezaDental;
    private BigDecimal numeroPieza;
    private String estado;
    private String observacion;
}

