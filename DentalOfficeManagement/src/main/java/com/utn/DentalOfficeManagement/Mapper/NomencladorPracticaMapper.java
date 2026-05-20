package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.NomencladorPracticaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.NomencladorPracticaResponseDTO;
import com.utn.DentalOfficeManagement.Model.NomencladorPractica;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NomencladorPracticaMapper {

    @Mapping(source = "obraSocial.idObraSocial", target = "obraSocialId")
    @Mapping(source = "practica.idPractica", target = "practicaId")
    NomencladorPracticaResponseDTO entityToResponseDto(NomencladorPractica entity);

    @Mapping(target = "idNomencladorPractica", ignore = true)
    @Mapping(target = "obraSocial", ignore = true)
    @Mapping(target = "practica", ignore = true)
    NomencladorPractica requestDtoToEntity(NomencladorPracticaRequestDTO dto);

    @Mapping(target = "idNomencladorPractica", ignore = true)
    @Mapping(target = "obraSocial", ignore = true)
    @Mapping(target = "practica", ignore = true)
    void updateEntityFromRequestDto(NomencladorPracticaRequestDTO dto, @MappingTarget NomencladorPractica entity);
}

