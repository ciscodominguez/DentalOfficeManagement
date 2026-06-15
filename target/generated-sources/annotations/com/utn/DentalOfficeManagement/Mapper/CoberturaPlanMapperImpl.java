package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.CoberturaPlanRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.CoberturaPlanResponseDTO;
import com.utn.DentalOfficeManagement.Model.CoberturaPlan;
import com.utn.DentalOfficeManagement.Model.Plan;
import com.utn.DentalOfficeManagement.Model.Practica;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class CoberturaPlanMapperImpl implements CoberturaPlanMapper {

    @Override
    public CoberturaPlanResponseDTO entityToResponseDto(CoberturaPlan entity) {
        if ( entity == null ) {
            return null;
        }

        CoberturaPlanResponseDTO coberturaPlanResponseDTO = new CoberturaPlanResponseDTO();

        coberturaPlanResponseDTO.setPlanId( entityPlanIdPlan( entity ) );
        coberturaPlanResponseDTO.setPracticaId( entityPracticaIdPractica( entity ) );
        coberturaPlanResponseDTO.setIdCoberturaPlan( entity.getIdCoberturaPlan() );
        coberturaPlanResponseDTO.setPorcentajeCobertura( entity.getPorcentajeCobertura() );
        coberturaPlanResponseDTO.setRequiereAutorizacion( entity.getRequiereAutorizacion() );

        return coberturaPlanResponseDTO;
    }

    @Override
    public CoberturaPlan requestDtoToEntity(CoberturaPlanRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        CoberturaPlan coberturaPlan = new CoberturaPlan();

        coberturaPlan.setPorcentajeCobertura( dto.getPorcentajeCobertura() );
        coberturaPlan.setRequiereAutorizacion( dto.getRequiereAutorizacion() );

        return coberturaPlan;
    }

    @Override
    public void updateEntityFromRequestDto(CoberturaPlanRequestDTO dto, CoberturaPlan entity) {
        if ( dto == null ) {
            return;
        }

        entity.setPorcentajeCobertura( dto.getPorcentajeCobertura() );
        entity.setRequiereAutorizacion( dto.getRequiereAutorizacion() );
    }

    private Long entityPlanIdPlan(CoberturaPlan coberturaPlan) {
        Plan plan = coberturaPlan.getPlan();
        if ( plan == null ) {
            return null;
        }
        return plan.getIdPlan();
    }

    private Long entityPracticaIdPractica(CoberturaPlan coberturaPlan) {
        Practica practica = coberturaPlan.getPractica();
        if ( practica == null ) {
            return null;
        }
        return practica.getIdPractica();
    }
}
