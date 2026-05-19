package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.UsuarioRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.UsuarioResponseDTO;
import com.utn.DentalOfficeManagement.Model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(source = "rol.idRol", target = "idRol")
    @Mapping(source = "rol.nombre", target = "rolNombre")
    UsuarioResponseDTO toResponse(Usuario usuario);

    @Mapping(target = "idUsuario", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "contrasenia", ignore = true)
    Usuario toEntity(UsuarioRequestDTO request);
}
