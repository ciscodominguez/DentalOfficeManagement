package com.utn.DentalOfficeManagement.DTO.Response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "AuthResponseDTO", description = "Respuesta con el token JWT")
public class AuthResponseDTO {

    @Schema(description = "Token JWT de acceso (se envía como 'Bearer <token>')")
    private String token;
}
