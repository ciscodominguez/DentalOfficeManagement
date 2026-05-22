package com.utn.DentalOfficeManagement.DTO.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
public class UsuarioRequestDTO {
    @NotBlank(message = "El username es obligatorio")
    @Size(max = 100)
    @Schema(description = "Nombre de usuario", example = "jdoe")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 255, message = "La contraseña debe tener entre 8 y 255 caracteres")
    @Schema(description = "Contraseña del usuario", example = "P4ssw0rd!")
    private String contrasenia;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    @Size(max = 150)
    @Schema(description = "Correo electrónico", example = "usuario@ejemplo.com")
    private String email;

    @NotNull(message = "El rol es obligatorio")
    @Schema(description = "Identificador del rol asociado", example = "2")
    private Long idRol;
}
