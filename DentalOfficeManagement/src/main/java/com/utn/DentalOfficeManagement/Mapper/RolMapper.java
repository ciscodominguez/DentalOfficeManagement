package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.RolRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.RolResponseDTO;
import com.utn.DentalOfficeManagement.Model.Rol;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolMapper {
    RolResponseDTO toResponse(Rol rol);
    Rol toEntity(RolRequestDTO request);
}

