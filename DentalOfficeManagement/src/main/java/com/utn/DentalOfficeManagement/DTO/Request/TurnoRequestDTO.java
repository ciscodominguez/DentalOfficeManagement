package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class TurnoRequestDTO {
    @NotNull(message = "El paciente es obligatorio")
    @Schema(description = "Identificador del paciente", example = "10")
    private Long pacienteId;

    @NotNull(message = "El odontólogo es obligatorio")
    @Schema(description = "Identificador del odontólogo", example = "5")
    private Long odontologoId;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser en el pasado")
    @Schema(description = "Fecha del turno (AAAA-MM-DD)", example = "2026-06-01")
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria")
    @Schema(description = "Hora del turno (HH:MM:SS)", example = "10:30:00")
    private LocalTime hora;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 50)
    @Schema(description = "Estado del turno", example = "CONFIRMADO")
    private String estado;

    @Schema(description = "Motivo del turno")
    private String motivo;
}
