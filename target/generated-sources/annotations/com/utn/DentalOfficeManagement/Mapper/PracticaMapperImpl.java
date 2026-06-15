package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PracticaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PracticaResponseDTO;
import com.utn.DentalOfficeManagement.Model.Practica;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class PracticaMapperImpl implements PracticaMapper {

    @Override
    public Practica requestDtoToEntity(PracticaRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Practica practica = new Practica();

        practica.setCodigoInterno( dto.getCodigoInterno() );
        practica.setNombre( dto.getNombre() );
        practica.setDescripcion( dto.getDescripcion() );
        practica.setPrecioBase( dto.getPrecioBase() );

        return practica;
    }

    @Override
    public PracticaResponseDTO entityToResponseDto(Practica entity) {
        if ( entity == null ) {
            return null;
        }

        PracticaResponseDTO practicaResponseDTO = new PracticaResponseDTO();

        practicaResponseDTO.setIdPractica( entity.getIdPractica() );
        practicaResponseDTO.setCodigoInterno( entity.getCodigoInterno() );
        practicaResponseDTO.setNombre( entity.getNombre() );
        practicaResponseDTO.setDescripcion( entity.getDescripcion() );
        practicaResponseDTO.setPrecioBase( entity.getPrecioBase() );

        return practicaResponseDTO;
    }

    @Override
    public void updateEntityFromRequestDto(PracticaRequestDTO dto, Practica entity) {
        if ( dto == null ) {
            return;
        }

        entity.setCodigoInterno( dto.getCodigoInterno() );
        entity.setNombre( dto.getNombre() );
        entity.setDescripcion( dto.getDescripcion() );
        entity.setPrecioBase( dto.getPrecioBase() );
    }
}
