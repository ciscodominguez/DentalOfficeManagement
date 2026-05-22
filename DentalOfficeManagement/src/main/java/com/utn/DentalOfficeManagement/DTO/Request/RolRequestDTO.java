package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
public class RolRequestDTO {
    @NotBlank(message = "El nombre del rol es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Schema(description = "Nombre del rol", example = "ADMIN")
    private String nombre;
}
