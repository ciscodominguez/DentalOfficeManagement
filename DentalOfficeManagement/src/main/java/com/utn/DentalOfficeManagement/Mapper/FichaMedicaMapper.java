package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.FichaMedicaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.FichaMedicaResponseDTO;
import com.utn.DentalOfficeManagement.Model.FichaMedica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FichaMedicaMapper {

    @Mapping(source = "paciente.idPaciente", target = "pacienteId")
    FichaMedicaResponseDTO entityToResponseDto(FichaMedica entity);

    @Mapping(target = "idFichaMedica", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    FichaMedica requestDtoToEntity(FichaMedicaRequestDTO dto);

    @Mapping(target = "idFichaMedica", ignore = true)
    @Mapping(target = "paciente", ignore = true)
    void updateEntityFromRequestDto(FichaMedicaRequestDTO dto, @MappingTarget FichaMedica entity);
}

