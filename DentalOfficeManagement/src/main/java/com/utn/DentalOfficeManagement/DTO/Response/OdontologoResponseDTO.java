package com.utn.DentalOfficeManagement.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "OdontologoResponseDTO", description = "Datos de un odontólogo")
public class OdontologoResponseDTO {
    @Schema(description = "Identificador del odontólogo", example = "5")
    private Long idOdontologo;
    @Schema(description = "Identificador del usuario asociado", example = "4")
    private Long usuarioId;
    @Schema(description = "Nombre del odontólogo", example = "María Gómez")
    private String nombre;
    @Schema(description = "Número de matrícula profesional", example = "MAT1234")
    private String matricula;
    @Schema(description = "Especialidad del odontólogo", example = "Ortodoncia")
    private String especialidad;
}

