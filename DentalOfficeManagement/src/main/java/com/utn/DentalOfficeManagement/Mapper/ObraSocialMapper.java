package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.ObraSocialRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.ObraSocialResponseDTO;
import com.utn.DentalOfficeManagement.Model.ObraSocial;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ObraSocialMapper {

    ObraSocialResponseDTO entityToResponseDto(ObraSocial entity);

    @org.mapstruct.Mapping(target = "idObraSocial", ignore = true)
    ObraSocial requestDtoToEntity(ObraSocialRequestDTO dto);

    @org.mapstruct.Mapping(target = "idObraSocial", ignore = true)
    void updateEntityFromRequestDto(ObraSocialRequestDTO dto, @MappingTarget ObraSocial entity);
}

