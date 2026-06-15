package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PlanRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PlanResponseDTO;
import com.utn.DentalOfficeManagement.Model.ObraSocial;
import com.utn.DentalOfficeManagement.Model.Plan;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class PlanMapperImpl implements PlanMapper {

    @Override
    public Plan requestDtoToEntity(PlanRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Plan plan = new Plan();

        plan.setNombre( dto.getNombre() );
        plan.setDescripcion( dto.getDescripcion() );

        return plan;
    }

    @Override
    public PlanResponseDTO entityToResponseDto(Plan entity) {
        if ( entity == null ) {
            return null;
        }

        PlanResponseDTO planResponseDTO = new PlanResponseDTO();

        planResponseDTO.setObraSocialId( entityObraSocialIdObraSocial( entity ) );
        planResponseDTO.setIdPlan( entity.getIdPlan() );
        planResponseDTO.setNombre( entity.getNombre() );
        planResponseDTO.setDescripcion( entity.getDescripcion() );

        return planResponseDTO;
    }

    @Override
    public void updateEntityFromRequestDto(PlanRequestDTO dto, Plan entity) {
        if ( dto == null ) {
            return;
        }

        entity.setNombre( dto.getNombre() );
        entity.setDescripcion( dto.getDescripcion() );
    }

    private Long entityObraSocialIdObraSocial(Plan plan) {
        ObraSocial obraSocial = plan.getObraSocial();
        if ( obraSocial == null ) {
            return null;
        }
        return obraSocial.getIdObraSocial();
    }
}
