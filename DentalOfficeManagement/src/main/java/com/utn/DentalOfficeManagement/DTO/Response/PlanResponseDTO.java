package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponseDTO {

    private Integer idPlan;
    private Integer obraSocialId;
    private String nombre;
    private String descripcion;
}

