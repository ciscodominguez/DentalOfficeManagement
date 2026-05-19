package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PagoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PagoResponseDTO;
import com.utn.DentalOfficeManagement.Model.Pago;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PagoMapper {

    Pago requestDtoToEntity(PagoRequestDTO dto);

    PagoResponseDTO entityToResponseDto(Pago entity);

    void updateEntityFromRequestDto(PagoRequestDTO dto, @MappingTarget Pago entity);
}

