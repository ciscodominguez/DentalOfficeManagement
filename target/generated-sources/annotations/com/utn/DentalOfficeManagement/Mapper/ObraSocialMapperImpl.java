package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.ObraSocialRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.ObraSocialResponseDTO;
import com.utn.DentalOfficeManagement.Model.ObraSocial;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class ObraSocialMapperImpl implements ObraSocialMapper {

    @Override
    public ObraSocialResponseDTO entityToResponseDto(ObraSocial entity) {
        if ( entity == null ) {
            return null;
        }

        ObraSocialResponseDTO obraSocialResponseDTO = new ObraSocialResponseDTO();

        obraSocialResponseDTO.setIdObraSocial( entity.getIdObraSocial() );
        obraSocialResponseDTO.setNombre( entity.getNombre() );
        obraSocialResponseDTO.setCuit( entity.getCuit() );
        obraSocialResponseDTO.setTelefono( entity.getTelefono() );

        return obraSocialResponseDTO;
    }

    @Override
    public ObraSocial requestDtoToEntity(ObraSocialRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ObraSocial obraSocial = new ObraSocial();

        obraSocial.setNombre( dto.getNombre() );
        obraSocial.setCuit( dto.getCuit() );
        obraSocial.setTelefono( dto.getTelefono() );

        return obraSocial;
    }

    @Override
    public void updateEntityFromRequestDto(ObraSocialRequestDTO dto, ObraSocial entity) {
        if ( dto == null ) {
            return;
        }

        entity.setNombre( dto.getNombre() );
        entity.setCuit( dto.getCuit() );
        entity.setTelefono( dto.getTelefono() );
    }
}
