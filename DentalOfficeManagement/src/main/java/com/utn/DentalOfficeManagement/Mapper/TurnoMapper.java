package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.TurnoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.TurnoResponseDTO;
import com.utn.DentalOfficeManagement.Model.Turno;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TurnoMapper {
    @Mapping(source = "paciente.idPaciente", target = "pacienteId")
    @Mapping(source = "paciente.nombre", target = "pacienteNombre")
    @Mapping(source = "paciente.dni", target = "pacienteDni")
    @Mapping(source = "odontologo.idOdontologo", target = "odontologoId")
    @Mapping(source = "odontologo.nombre", target = "odontologoNombre")
    @Mapping(source = "odontologo.matricula", target = "odontologoMatricula")
    TurnoResponseDTO toResponse(Turno turno);

    @Mapping(target = "idTurno", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    @Mapping(target = "odontologo", ignore = true)
    Turno toEntity(TurnoRequestDTO request);
}
