package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PracticaRealizadaResponseDTO {
    private Long idPracticaRealizada;
    private Long practicaId;
    private String practicaNombre;
    private Long piezaDentalId;
    private BigDecimal numeroPieza;
    private Long idNomencladorPractica;
    private String codigoExterno;
    private Long idCoberturaPlan;
    private BigDecimal porcentajeCobertura;
    private BigDecimal precioCobrado;
    private Long idPago;
    private String medioPago;
    private Long idTurno;
    private LocalDate fechaTurno;
}
