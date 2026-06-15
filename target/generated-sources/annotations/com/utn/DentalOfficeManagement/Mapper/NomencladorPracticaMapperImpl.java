package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.NomencladorPracticaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.NomencladorPracticaResponseDTO;
import com.utn.DentalOfficeManagement.Model.NomencladorPractica;
import com.utn.DentalOfficeManagement.Model.ObraSocial;
import com.utn.DentalOfficeManagement.Model.Practica;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class NomencladorPracticaMapperImpl implements NomencladorPracticaMapper {

    @Override
    public NomencladorPracticaResponseDTO entityToResponseDto(NomencladorPractica entity) {
        if ( entity == null ) {
            return null;
        }

        NomencladorPracticaResponseDTO nomencladorPracticaResponseDTO = new NomencladorPracticaResponseDTO();

        nomencladorPracticaResponseDTO.setObraSocialId( entityObraSocialIdObraSocial( entity ) );
        nomencladorPracticaResponseDTO.setPracticaId( entityPracticaIdPractica( entity ) );
        nomencladorPracticaResponseDTO.setIdNomencladorPractica( entity.getIdNomencladorPractica() );
        nomencladorPracticaResponseDTO.setCodigoExterno( entity.getCodigoExterno() );

        return nomencladorPracticaResponseDTO;
    }

    @Override
    public NomencladorPractica requestDtoToEntity(NomencladorPracticaRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        NomencladorPractica nomencladorPractica = new NomencladorPractica();

        nomencladorPractica.setCodigoExterno( dto.getCodigoExterno() );

        return nomencladorPractica;
    }

    @Override
    public void updateEntityFromRequestDto(NomencladorPracticaRequestDTO dto, NomencladorPractica entity) {
        if ( dto == null ) {
            return;
        }

        entity.setCodigoExterno( dto.getCodigoExterno() );
    }

    private Long entityObraSocialIdObraSocial(NomencladorPractica nomencladorPractica) {
        ObraSocial obraSocial = nomencladorPractica.getObraSocial();
        if ( obraSocial == null ) {
            return null;
        }
        return obraSocial.getIdObraSocial();
    }

    private Long entityPracticaIdPractica(NomencladorPractica nomencladorPractica) {
        Practica practica = nomencladorPractica.getPractica();
        if ( practica == null ) {
            return null;
        }
        return practica.getIdPractica();
    }
}
