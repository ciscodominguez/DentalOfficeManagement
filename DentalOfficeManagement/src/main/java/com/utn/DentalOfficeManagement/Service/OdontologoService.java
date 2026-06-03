package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.OdontologoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.OdontologoResponseDTO;
import com.utn.DentalOfficeManagement.Exception.DuplicateResourceException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.OdontologoMapper;
import com.utn.DentalOfficeManagement.Model.Odontologo;
import com.utn.DentalOfficeManagement.Model.Usuario;
import com.utn.DentalOfficeManagement.Repository.OdontologoRepository;
import com.utn.DentalOfficeManagement.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OdontologoService {

    private final OdontologoRepository odontologoRepository;
    private final UsuarioRepository usuarioRepository;
    private final OdontologoMapper odontologoMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, UsuarioRepository usuarioRepository, OdontologoMapper odontologoMapper) {
        this.odontologoRepository = odontologoRepository;
        this.usuarioRepository = usuarioRepository;
        this.odontologoMapper = odontologoMapper;
    }

    public OdontologoResponseDTO crearOdontologo(OdontologoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getUsuarioId()));

        if (odontologoRepository.existsByMatricula(dto.getMatricula())) {
            throw new DuplicateResourceException("Odontólogo", "matrícula", dto.getMatricula());
        }

        Odontologo odontologo = odontologoMapper.requestDtoToEntity(dto);
        odontologo.setUsuario(usuario);
        Odontologo odontologoGuardado = odontologoRepository.save(odontologo);
        return odontologoMapper.entityToResponseDto(odontologoGuardado);
    }

    @Transactional(readOnly = true)
    public OdontologoResponseDTO obtenerOdontologoPorId(Long id) {
        Odontologo odontologo = odontologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Odontólogo", "id", id));
        return odontologoMapper.entityToResponseDto(odontologo);
    }

    @Transactional(readOnly = true)
    public List<OdontologoResponseDTO> listarTodos() {
        return odontologoRepository.findAll().stream()
                .map(odontologoMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OdontologoResponseDTO> buscarPorNombre(String nombre) {
        return odontologoRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(odontologoMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<OdontologoResponseDTO> buscarPorEspecialidad(String especialidad) {
        return odontologoRepository.findByEspecialidadContainingIgnoreCase(especialidad).stream()
                .map(odontologoMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public OdontologoResponseDTO actualizarOdontologo(Long id, OdontologoRequestDTO dto) {
        Odontologo odontologo = odontologoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Odontólogo", "id", id));

        if (odontologoRepository.existsByMatriculaAndIdOdontologoNot(dto.getMatricula(), id)) {
            throw new DuplicateResourceException("Odontólogo", "matrícula", dto.getMatricula());
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", dto.getUsuarioId()));

        odontologo.setUsuario(usuario);
        odontologo.setNombre(dto.getNombre());
        odontologo.setMatricula(dto.getMatricula());
        odontologo.setEspecialidad(dto.getEspecialidad());
        Odontologo odontologoActualizado = odontologoRepository.save(odontologo);
        return odontologoMapper.entityToResponseDto(odontologoActualizado);
    }

    public void eliminarOdontologo(Long id) {
        if (!odontologoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Odontólogo", "id", id);
        }
        odontologoRepository.deleteById(id);
    }
}

