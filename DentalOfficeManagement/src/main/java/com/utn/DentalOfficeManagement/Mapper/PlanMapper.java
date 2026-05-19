package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PlanRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PlanResponseDTO;
import com.utn.DentalOfficeManagement.Model.Plan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlanMapper {

    @Mapping(target = "obraSocial", ignore = true)
    Plan requestDtoToEntity(PlanRequestDTO dto);

    @Mapping(target = "obraSocialId", source = "obraSocial.idObraSocial")
    PlanResponseDTO entityToResponseDto(Plan entity);

    @Mapping(target = "idPlan", ignore = true)
    @Mapping(target = "obraSocial", ignore = true)
    void updateEntityFromRequestDto(PlanRequestDTO dto, @MappingTarget Plan entity);
}

