package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UsuarioResponseDTO", description = "Datos básicos del usuario")
public class UsuarioResponseDTO {
    @Schema(description = "Identificador del usuario", example = "1")
    private Long idUsuario;
    @Schema(description = "Nombre de usuario", example = "jdoe")
    private String username;
    @Schema(description = "Correo electrónico", example = "usuario@ejemplo.com")
    private String email;
    @Schema(description = "Identificador del rol asociado", example = "2")
    private Long idRol;
    @Schema(description = "Nombre del rol asociado", example = "ADMIN")
    private String rolNombre;
}
