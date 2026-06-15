package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PiezaDentalRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PiezaDentalResponseDTO;
import com.utn.DentalOfficeManagement.Model.PiezaDental;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class PiezaDentalMapperImpl implements PiezaDentalMapper {

    @Override
    public PiezaDental requestDtoToEntity(PiezaDentalRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        PiezaDental piezaDental = new PiezaDental();

        piezaDental.setIdPiezaDental( dto.getIdPiezaDental() );
        piezaDental.setNumeroPieza( dto.getNumeroPieza() );
        piezaDental.setEstado( dto.getEstado() );
        piezaDental.setObservacion( dto.getObservacion() );

        return piezaDental;
    }

    @Override
    public PiezaDentalResponseDTO entityToResponseDto(PiezaDental entity) {
        if ( entity == null ) {
            return null;
        }

        PiezaDentalResponseDTO piezaDentalResponseDTO = new PiezaDentalResponseDTO();

        piezaDentalResponseDTO.setIdPiezaDental( entity.getIdPiezaDental() );

        return piezaDentalResponseDTO;
    }

    @Override
    public void updateEntityFromRequestDto(PiezaDentalRequestDTO dto, PiezaDental entity) {
        if ( dto == null ) {
            return;
        }

        entity.setIdPiezaDental( dto.getIdPiezaDental() );
        entity.setNumeroPieza( dto.getNumeroPieza() );
        entity.setEstado( dto.getEstado() );
        entity.setObservacion( dto.getObservacion() );
    }
}
