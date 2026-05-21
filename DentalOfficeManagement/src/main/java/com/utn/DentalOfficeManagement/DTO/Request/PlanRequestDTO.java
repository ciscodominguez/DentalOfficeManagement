package com.utn.DentalOfficeManagement.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequestDTO {

    @NotNull(message = "El id de obra social no puede ser nulo")
    private Long obraSocialId;

    @NotBlank(message = "El nombre del plan no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La descripción del plan no puede estar vacía")
    private String descripcion;
}

