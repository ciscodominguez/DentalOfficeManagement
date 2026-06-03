package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.PiezaDentalRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PiezaDentalResponseDTO;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.PiezaDentalMapper;
import com.utn.DentalOfficeManagement.Model.PiezaDental;
import com.utn.DentalOfficeManagement.Repository.PiezaDentalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PiezaDentalService {

    private final PiezaDentalRepository piezaDentalRepository;
    private final PiezaDentalMapper piezaDentalMapper;

    public PiezaDentalService(PiezaDentalRepository piezaDentalRepository, PiezaDentalMapper piezaDentalMapper) {
        this.piezaDentalRepository = piezaDentalRepository;
        this.piezaDentalMapper = piezaDentalMapper;
    }

    public PiezaDentalResponseDTO crearPiezaDental(PiezaDentalRequestDTO dto) {
        PiezaDental piezaDental = piezaDentalMapper.requestDtoToEntity(dto);
        PiezaDental piezaDentalGuardada = piezaDentalRepository.save(piezaDental);
        return piezaDentalMapper.entityToResponseDto(piezaDentalGuardada);
    }

    @Transactional(readOnly = true)
    public PiezaDentalResponseDTO obtenerPiezaDentalPorId(Long id) {
        PiezaDental piezaDental = piezaDentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pieza Dental", "id", id));
        return piezaDentalMapper.entityToResponseDto(piezaDental);
    }

    @Transactional(readOnly = true)
    public List<PiezaDentalResponseDTO> listarTodas() {
        return piezaDentalRepository.findAll().stream()
                .map(piezaDentalMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PiezaDentalResponseDTO> listarPiezasDentalesdePaciente(Long idPaciente) {
        return piezaDentalRepository.findByFichaMedica_Paciente_IdPaciente(idPaciente).stream()
                .map(piezaDentalMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public PiezaDentalResponseDTO actualizarPiezaDental(Long id, PiezaDentalRequestDTO dto) {
        PiezaDental piezaDental = piezaDentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pieza Dental", "id", id));

        piezaDental.setNumeroPieza(dto.getNumeroPieza());
        piezaDental.setEstado(dto.getEstado());
        piezaDental.setObservacion(dto.getObservacion());
        PiezaDental piezaDentalActualizada = piezaDentalRepository.save(piezaDental);
        return piezaDentalMapper.entityToResponseDto(piezaDentalActualizada);
    }

    public void eliminarPiezaDental(Long id) {
        if (!piezaDentalRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pieza Dental", "id", id);
        }
        piezaDentalRepository.deleteById(id);
    }
}

