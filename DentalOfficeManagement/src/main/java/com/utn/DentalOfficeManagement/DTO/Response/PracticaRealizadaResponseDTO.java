package com.utn.DentalOfficeManagement.DTO.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa los datos devueltos de una práctica realizada en un turno médico, incluyendo detalles de cobertura y pago")
public class PracticaRealizadaResponseDTO {
    @Schema(description = "Identificador único del registro de la práctica realizada", example = "550")
    private Long idPracticaRealizada;

    @Schema(description = "ID de la práctica base (catálogo)", example = "12")
    private Long practicaId;

    @Schema(description = "Nombre o descripción de la práctica efectuada", example = "Conducto Radicular (Endodoncia)")
    private String practicaNombre;

    @Schema(description = "ID de la pieza dental asociada (si aplica a una pieza específica)", example = "8")
    private Long piezaDentalId;

    @Schema(description = "Número comercial o internacional de la pieza dental intervenida", example = "46")
    private BigDecimal numeroPieza;

    @Schema(description = "ID del nomenclador de la práctica según regulaciones", example = "3")
    private Long idNomencladorPractica;

    @Schema(description = "Código externo oficial del nomenclador odontológico", example = "02.01.01")
    private String codigoExterno;

    @Schema(description = "ID del plan de cobertura/obra social aplicado", example = "105")
    private Long idCoberturaPlan;

    @Schema(description = "Porcentaje cubierto por la obra social o prepaga", example = "80.00")
    private BigDecimal porcentajeCobertura;

    @Schema(description = "Monto final cobrado al paciente (copago o particular)", example = "15000.50")
    private BigDecimal precioCobrado;

    @Schema(description = "ID del registro de pago asociado", example = "1024")
    private Long idPago;

    @Schema(description = "Medio a través del cual se abonó la práctica", example = "Tarjeta de Débito")
    private String medioPago;

    @Schema(description = "ID del turno médico en el que se realizó el tratamiento", example = "871")
    private Long idTurno;

    @Schema(description = "Fecha exacta en la que se llevó a cabo el turno y la práctica", example = "2026-05-22")
    private LocalDate fechaTurno;
}
