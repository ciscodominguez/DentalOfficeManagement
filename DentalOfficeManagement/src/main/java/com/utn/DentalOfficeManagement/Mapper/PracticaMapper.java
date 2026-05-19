package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PracticaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PracticaResponseDTO;
import com.utn.DentalOfficeManagement.Model.Practica;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PracticaMapper {

    Practica requestDtoToEntity(PracticaRequestDTO dto);

    PracticaResponseDTO entityToResponseDto(Practica entity);

    void updateEntityFromRequestDto(PracticaRequestDTO dto, @MappingTarget Practica entity);
}

