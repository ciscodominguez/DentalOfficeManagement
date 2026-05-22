package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.PracticaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PracticaResponseDTO;
import com.utn.DentalOfficeManagement.Exception.DuplicateResourceException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.PracticaMapper;
import com.utn.DentalOfficeManagement.Model.Practica;
import com.utn.DentalOfficeManagement.Repository.PracticaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PracticaService {

    private final PracticaRepository practicaRepository;
    private final PracticaMapper practicaMapper;

    public PracticaService(PracticaRepository practicaRepository, PracticaMapper practicaMapper) {
        this.practicaRepository = practicaRepository;
        this.practicaMapper = practicaMapper;
    }

    public PracticaResponseDTO crearPractica(PracticaRequestDTO dto) {
        if (practicaRepository.existsByCodigoInterno(dto.getCodigoInterno())) {
            throw new DuplicateResourceException("Práctica", "código interno", dto.getCodigoInterno());
        }

        Practica practica = practicaMapper.requestDtoToEntity(dto);
        Practica practicaGuardada = practicaRepository.save(practica);
        return practicaMapper.entityToResponseDto(practicaGuardada);
    }

    @Transactional(readOnly = true)
    public PracticaResponseDTO obtenerPracticaPorId(Integer id) {
        Practica practica = practicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Práctica", "id", id));
        return practicaMapper.entityToResponseDto(practica);
    }

    @Transactional(readOnly = true)
    public List<PracticaResponseDTO> listarTodas() {
        return practicaRepository.findAll().stream()
                .map(practicaMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PracticaResponseDTO> buscarPorNombre(String nombre) {
        return practicaRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(practicaMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public PracticaResponseDTO actualizarPractica(Integer id, PracticaRequestDTO dto) {
        Practica practica = practicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Práctica", "id", id));

        if (practicaRepository.existsByCodigoInternoAndIdPracticaNot(dto.getCodigoInterno(), id)) {
            throw new DuplicateResourceException("Práctica", "código interno", dto.getCodigoInterno());
        }

        practica.setCodigoInterno(dto.getCodigoInterno());
        practica.setNombre(dto.getNombre());
        practica.setDescripcion(dto.getDescripcion());
        practica.setPrecioBase(dto.getPrecioBase());
        Practica practicaActualizada = practicaRepository.save(practica);
        return practicaMapper.entityToResponseDto(practicaActualizada);
    }

    public void eliminarPractica(Integer id) {
        if (!practicaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Práctica", "id", id);
        }
        practicaRepository.deleteById(id);
    }
}

