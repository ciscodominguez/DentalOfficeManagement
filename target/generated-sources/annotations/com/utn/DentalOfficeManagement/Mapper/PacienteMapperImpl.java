package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PacienteRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PacienteResponseDTO;
import com.utn.DentalOfficeManagement.Model.Paciente;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class PacienteMapperImpl implements PacienteMapper {

    @Override
    public Paciente requestDtoToEntity(PacienteRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Paciente paciente = new Paciente();

        paciente.setNombre( dto.getNombre() );
        paciente.setDni( dto.getDni() );
        paciente.setFechaNacimiento( dto.getFechaNacimiento() );
        paciente.setTelefono( dto.getTelefono() );
        paciente.setNroAfiliado( dto.getNroAfiliado() );
        paciente.setIsEmbarazada( dto.getIsEmbarazada() );

        return paciente;
    }

    @Override
    public PacienteResponseDTO entityToResponseDto(Paciente entity) {
        if ( entity == null ) {
            return null;
        }

        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO();

        pacienteResponseDTO.setIdPaciente( entity.getIdPaciente() );
        pacienteResponseDTO.setNombre( entity.getNombre() );
        pacienteResponseDTO.setDni( entity.getDni() );
        pacienteResponseDTO.setFechaNacimiento( entity.getFechaNacimiento() );
        pacienteResponseDTO.setTelefono( entity.getTelefono() );
        pacienteResponseDTO.setNroAfiliado( entity.getNroAfiliado() );
        pacienteResponseDTO.setIsEmbarazada( entity.getIsEmbarazada() );

        return pacienteResponseDTO;
    }

    @Override
    public void updateEntityFromRequestDto(PacienteRequestDTO dto, Paciente entity) {
        if ( dto == null ) {
            return;
        }

        entity.setNombre( dto.getNombre() );
        entity.setDni( dto.getDni() );
        entity.setFechaNacimiento( dto.getFechaNacimiento() );
        entity.setTelefono( dto.getTelefono() );
        entity.setNroAfiliado( dto.getNroAfiliado() );
        entity.setIsEmbarazada( dto.getIsEmbarazada() );
    }
}
