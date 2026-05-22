package com.utn.DentalOfficeManagement.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteRequestDTO {

    @NotNull(message = "El id del plan no puede ser nulo")
    @Schema(description = "Identificador del plan asociado", example = "2")
    private Long idPlan;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Schema(description = "Nombre del paciente", example = "Juan Perez")
    private String nombre;

    @NotBlank(message = "El DNI no puede estar vacío")
    @Pattern(regexp = "^[0-9]{7,8}$", message = "El DNI debe contener entre 7 y 8 dígitos")
    @Schema(description = "Documento Nacional de Identidad", example = "12345678")
    private String dni;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Schema(description = "Fecha de nacimiento (AAAA-MM-DD)", example = "1990-01-01")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(regexp = "^[0-9\\-\\+\\s\\(\\)]+$", message = "El teléfono contiene caracteres inválidos")
    @Schema(description = "Teléfono de contacto", example = "+5491123456789")
    private String telefono;

    @NotBlank(message = "El número de afiliado no puede estar vacío")
    @Schema(description = "Número de afiliado en la obra social", example = "AFF12345")
    private String nroAfiliado;

    @Schema(description = "Indica si la paciente está embarazada")
    private Boolean isEmbarazada;
}

