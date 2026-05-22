package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RolResponseDTO", description = "Datos de un rol")
public class RolResponseDTO {
    @Schema(description = "Identificador del rol", example = "1")
    private Long idRol;
    @Schema(description = "Nombre del rol", example = "ADMIN")
    private String nombre;
}
