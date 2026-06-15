package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.FichaMedicaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.FichaMedicaResponseDTO;
import com.utn.DentalOfficeManagement.Model.FichaMedica;
import com.utn.DentalOfficeManagement.Model.Paciente;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class FichaMedicaMapperImpl implements FichaMedicaMapper {

    @Override
    public FichaMedicaResponseDTO entityToResponseDto(FichaMedica entity) {
        if ( entity == null ) {
            return null;
        }

        FichaMedicaResponseDTO fichaMedicaResponseDTO = new FichaMedicaResponseDTO();

        fichaMedicaResponseDTO.setPacienteId( entityPacienteIdPaciente( entity ) );
        fichaMedicaResponseDTO.setIdFichaMedica( entity.getIdFichaMedica() );
        fichaMedicaResponseDTO.setAlergias( entity.getAlergias() );
        fichaMedicaResponseDTO.setMedicacion( entity.getMedicacion() );
        fichaMedicaResponseDTO.setAntecedentes( entity.getAntecedentes() );
        fichaMedicaResponseDTO.setGrupoSanguineo( entity.getGrupoSanguineo() );

        return fichaMedicaResponseDTO;
    }

    @Override
    public FichaMedica requestDtoToEntity(FichaMedicaRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        FichaMedica fichaMedica = new FichaMedica();

        fichaMedica.setAlergias( dto.getAlergias() );
        fichaMedica.setMedicacion( dto.getMedicacion() );
        fichaMedica.setAntecedentes( dto.getAntecedentes() );
        fichaMedica.setGrupoSanguineo( dto.getGrupoSanguineo() );

        return fichaMedica;
    }

    @Override
    public void updateEntityFromRequestDto(FichaMedicaRequestDTO dto, FichaMedica entity) {
        if ( dto == null ) {
            return;
        }

        entity.setAlergias( dto.getAlergias() );
        entity.setMedicacion( dto.getMedicacion() );
        entity.setAntecedentes( dto.getAntecedentes() );
        entity.setGrupoSanguineo( dto.getGrupoSanguineo() );
    }

    private Long entityPacienteIdPaciente(FichaMedica fichaMedica) {
        Paciente paciente = fichaMedica.getPaciente();
        if ( paciente == null ) {
            return null;
        }
        return paciente.getIdPaciente();
    }
}
