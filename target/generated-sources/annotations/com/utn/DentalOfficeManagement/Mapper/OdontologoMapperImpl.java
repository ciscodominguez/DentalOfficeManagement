package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.OdontologoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.OdontologoResponseDTO;
import com.utn.DentalOfficeManagement.Model.Odontologo;
import com.utn.DentalOfficeManagement.Model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class OdontologoMapperImpl implements OdontologoMapper {

    @Override
    public OdontologoResponseDTO entityToResponseDto(Odontologo entity) {
        if ( entity == null ) {
            return null;
        }

        OdontologoResponseDTO odontologoResponseDTO = new OdontologoResponseDTO();

        odontologoResponseDTO.setUsuarioId( entityUsuarioIdUsuario( entity ) );
        odontologoResponseDTO.setIdOdontologo( entity.getIdOdontologo() );
        odontologoResponseDTO.setNombre( entity.getNombre() );
        odontologoResponseDTO.setMatricula( entity.getMatricula() );
        odontologoResponseDTO.setEspecialidad( entity.getEspecialidad() );

        return odontologoResponseDTO;
    }

    @Override
    public Odontologo requestDtoToEntity(OdontologoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Odontologo odontologo = new Odontologo();

        odontologo.setNombre( dto.getNombre() );
        odontologo.setMatricula( dto.getMatricula() );
        odontologo.setEspecialidad( dto.getEspecialidad() );

        return odontologo;
    }

    @Override
    public void updateEntityFromRequestDto(OdontologoRequestDTO dto, Odontologo entity) {
        if ( dto == null ) {
            return;
        }

        entity.setNombre( dto.getNombre() );
        entity.setMatricula( dto.getMatricula() );
        entity.setEspecialidad( dto.getEspecialidad() );
    }

    private Long entityUsuarioIdUsuario(Odontologo odontologo) {
        Usuario usuario = odontologo.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getIdUsuario();
    }
}
