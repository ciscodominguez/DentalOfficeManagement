package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoberturaPlanResponseDTO {
    private Integer idCoberturaPlan;
    private Integer planId;
    private Integer practicaId;
    private BigDecimal porcentajeCobertura;
    private Boolean requiereAutorizacion;
}

