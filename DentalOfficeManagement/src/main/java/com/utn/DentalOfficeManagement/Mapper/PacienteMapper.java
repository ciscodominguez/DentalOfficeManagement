package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PacienteRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PacienteResponseDTO;
import com.utn.DentalOfficeManagement.Model.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    @Mapping(target = "plan", ignore = true)
    Paciente requestDtoToEntity(PacienteRequestDTO dto);

    @Mapping(target = "idPlan", source = "plan.idPlan")
    PacienteResponseDTO entityToResponseDto(Paciente entity);

    @Mapping(target = "idPaciente", ignore = true)
    @Mapping(target = "plan", ignore = true)
    void updateEntityFromRequestDto(PacienteRequestDTO dto, @MappingTarget Paciente entity);
}

