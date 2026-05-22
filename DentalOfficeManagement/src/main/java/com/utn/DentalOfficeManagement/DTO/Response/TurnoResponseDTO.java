package com.utn.DentalOfficeManagement.DTO.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO que representa la información detallada de un turno programado en la clínica")
public class TurnoResponseDTO {

    @Schema(description = "Identificador único del turno", example = "871")
    private Long idTurno;

    @Schema(description = "ID del paciente que reservó el turno", example = "45")
    private Long pacienteId;

    @Schema(description = "Nombre y apellido completo del paciente", example = "Juan Carlos Pérez")
    private String pacienteNombre;

    @Schema(description = "Documento Nacional de Identidad del paciente", example = "38456123")
    private String pacienteDni;

    @Schema(description = "ID del odontólogo asignado para el turno", example = "7")
    private Long odontologoId;

    @Schema(description = "Nombre y apellido completo del odontólogo", example = "Dra. María Laura Giménez")
    private String odontologoNombre;

    @Schema(description = "Matrícula profesional del odontólogo", example = "MP-45982")
    private String odontologoMatricula;

    @Schema(description = "Fecha programada para el turno", example = "2026-05-25")
    private LocalDate fecha;

    @Schema(description = "Hora programada para el inicio del turno (Formato 24hs)", example = "16:30:00")
    private LocalTime hora;

    @Schema(description = "Estado actual en el que se encuentra el turno",
            allowableValues = {"PENDIENTE", "CONFIRMADO", "ATENDIDO", "CANCELADO"},
            example = "CONFIRMADO")
    private String estado;

    @Schema(description = "Breve motivo de la consulta o síntoma referido por el paciente",
            example = "Dolor agudo en el molar inferior derecho")
    private String motivo;
}
