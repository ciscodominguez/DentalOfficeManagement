package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {

    private Integer idPaciente;
    private Integer idPlan;
    private String nombre;
    private String dni;
    private LocalDate fechaNacimiento;
    private String telefono;
    private String nroAfiliado;
    private Boolean isEmbarazada;
}

