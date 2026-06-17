package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PiezaDentalRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PiezaDentalResponseDTO;
import com.utn.DentalOfficeManagement.Model.PiezaDental;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PiezaDentalMapper {

    PiezaDental requestDtoToEntity(PiezaDentalRequestDTO dto);

    @Mapping(target = "numeroPieza", source = "numeroPieza")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "observacion", source = "observacion")
    PiezaDentalResponseDTO entityToResponseDto(PiezaDental entity);

    void updateEntityFromRequestDto(PiezaDentalRequestDTO dto, @MappingTarget PiezaDental entity);
}

