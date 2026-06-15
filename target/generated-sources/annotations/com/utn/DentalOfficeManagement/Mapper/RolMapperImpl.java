package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.RolRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.RolResponseDTO;
import com.utn.DentalOfficeManagement.Model.Rol;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class RolMapperImpl implements RolMapper {

    @Override
    public RolResponseDTO toResponse(Rol rol) {
        if ( rol == null ) {
            return null;
        }

        RolResponseDTO rolResponseDTO = new RolResponseDTO();

        rolResponseDTO.setIdRol( rol.getIdRol() );
        rolResponseDTO.setNombre( rol.getNombre() );

        return rolResponseDTO;
    }

    @Override
    public Rol toEntity(RolRequestDTO request) {
        if ( request == null ) {
            return null;
        }

        Rol rol = new Rol();

        rol.setNombre( request.getNombre() );

        return rol;
    }
}
