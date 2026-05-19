package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class TurnoRequestDTO {
    @NotNull(message = "El paciente es obligatorio")
    private Long pacienteId;

    @NotNull(message = "El odontólogo es obligatorio")
    private Long odontologoId;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La fecha no puede ser en el pasado")
    private LocalDate fecha;

    @NotNull(message = "La hora es obligatoria")
    private LocalTime hora;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 50)
    private String estado;

    private String motivo;
}
