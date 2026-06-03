package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.RolRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.RolResponseDTO;
import com.utn.DentalOfficeManagement.Exception.DuplicateResourceException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.RolMapper;
import com.utn.DentalOfficeManagement.Model.Rol;
import com.utn.DentalOfficeManagement.Repository.RolRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;

    public RolService(RolRepository rolRepository, RolMapper rolMapper) {
        this.rolRepository = rolRepository;
        this.rolMapper = rolMapper;
    }

    public RolResponseDTO crearRol(RolRequestDTO dto) {
        if (rolRepository.existsByNombre(dto.getNombre())) {
            throw new DuplicateResourceException("Rol", "nombre", dto.getNombre());
        }
        Rol rol = rolMapper.toEntity(dto);
        Rol rolGuardado = rolRepository.save(rol);
        return rolMapper.toResponse(rolGuardado);
    }

    @Transactional(readOnly = true)
    public RolResponseDTO obtenerRolPorId(Long id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));
        return rolMapper.toResponse(rol);
    }

    @Transactional(readOnly = true)
    public List<RolResponseDTO> listarTodosLosRoles() {
        return rolRepository.findAll().stream()
                .map(rolMapper::toResponse)
                .collect(Collectors.toList());
    }

    public RolResponseDTO actualizarRol(Long id, RolRequestDTO dto) {       Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", "id", id));

        if (rolRepository.existsByNombreAndIdRolNot(dto.getNombre(), id)) {
            throw new DuplicateResourceException("Rol", "nombre", dto.getNombre());
        }

        rol.setNombre(dto.getNombre());
        Rol rolActualizado = rolRepository.save(rol);
        return rolMapper.toResponse(rolActualizado);
    }

    public void eliminarRol(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rol", "id", id);
        }
        rolRepository.deleteById(id);
    }
}


