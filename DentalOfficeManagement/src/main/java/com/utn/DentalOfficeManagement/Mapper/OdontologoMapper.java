package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.OdontologoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.OdontologoResponseDTO;
import com.utn.DentalOfficeManagement.Model.Odontologo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OdontologoMapper {

    @Mapping(source = "usuario.idUsuario", target = "usuarioId")
    OdontologoResponseDTO entityToResponseDto(Odontologo entity);

    @Mapping(target = "idOdontologo", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Odontologo requestDtoToEntity(OdontologoRequestDTO dto);

    @Mapping(target = "idOdontologo", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    void updateEntityFromRequestDto(OdontologoRequestDTO dto, @MappingTarget Odontologo entity);
}

