package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.CoberturaPlanRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.CoberturaPlanResponseDTO;
import com.utn.DentalOfficeManagement.Model.CoberturaPlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CoberturaPlanMapper {

    @Mapping(source = "plan.idPlan", target = "planId")
    @Mapping(source = "practica.idPractica", target = "practicaId")
    CoberturaPlanResponseDTO entityToResponseDto(CoberturaPlan entity);

    @Mapping(target = "idCoberturaPlan", ignore = true)
    @Mapping(target = "plan", ignore = true)
    @Mapping(target = "practica", ignore = true)
    CoberturaPlan requestDtoToEntity(CoberturaPlanRequestDTO dto);

    @Mapping(target = "idCoberturaPlan", ignore = true)
    @Mapping(target = "plan", ignore = true)
    @Mapping(target = "practica", ignore = true)
    void updateEntityFromRequestDto(CoberturaPlanRequestDTO dto, @MappingTarget CoberturaPlan entity);
}

