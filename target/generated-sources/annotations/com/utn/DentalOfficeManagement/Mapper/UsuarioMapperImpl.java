package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.UsuarioRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.UsuarioResponseDTO;
import com.utn.DentalOfficeManagement.Model.Rol;
import com.utn.DentalOfficeManagement.Model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioResponseDTO toResponse(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioResponseDTO usuarioResponseDTO = new UsuarioResponseDTO();

        usuarioResponseDTO.setIdRol( usuarioRolIdRol( usuario ) );
        usuarioResponseDTO.setRolNombre( usuarioRolNombre( usuario ) );
        usuarioResponseDTO.setIdUsuario( usuario.getIdUsuario() );
        usuarioResponseDTO.setUsername( usuario.getUsername() );
        usuarioResponseDTO.setEmail( usuario.getEmail() );

        return usuarioResponseDTO;
    }

    @Override
    public Usuario toEntity(UsuarioRequestDTO request) {
        if ( request == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setUsername( request.getUsername() );
        usuario.setEmail( request.getEmail() );

        return usuario;
    }

    private Long usuarioRolIdRol(Usuario usuario) {
        Rol rol = usuario.getRol();
        if ( rol == null ) {
            return null;
        }
        return rol.getIdRol();
    }

    private String usuarioRolNombre(Usuario usuario) {
        Rol rol = usuario.getRol();
        if ( rol == null ) {
            return null;
        }
        return rol.getNombre();
    }
}
