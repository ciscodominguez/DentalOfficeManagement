package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "PacienteResponseDTO", description = "Datos devueltos de un paciente")
public class PacienteResponseDTO {

    @Schema(description = "Identificador del paciente", example = "10")
    private Long idPaciente;
    @Schema(description = "Identificador del plan asociado", example = "2")
    private Long idPlan;
    @Schema(description = "Nombre del paciente", example = "Juan Perez")
    private String nombre;
    @Schema(description = "Documento Nacional de Identidad", example = "12345678")
    private String dni;
    @Schema(description = "Fecha de nacimiento (AAAA-MM-DD)", example = "1990-01-01")
    private LocalDate fechaNacimiento;
    @Schema(description = "Teléfono de contacto", example = "+5491123456789")
    private String telefono;
    @Schema(description = "Número de afiliado", example = "AFF12345")
    private String nroAfiliado;
    @Schema(description = "Indica si la paciente está embarazada")
    private Boolean isEmbarazada;
}

