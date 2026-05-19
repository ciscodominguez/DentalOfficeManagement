package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnoResponseDTO {
    private Long idTurno;
    private Long pacienteId;
    private String pacienteNombre;
    private String pacienteDni;
    private Long odontologoId;
    private String odontologoNombre;
    private String odontologoMatricula;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private String motivo;
}
