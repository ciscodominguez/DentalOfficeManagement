package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.PacienteRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PacienteResponseDTO;
import com.utn.DentalOfficeManagement.Exception.DuplicateResourceException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.PacienteMapper;
import com.utn.DentalOfficeManagement.Model.Paciente;
import com.utn.DentalOfficeManagement.Model.Plan;
import com.utn.DentalOfficeManagement.Repository.PacienteRepository;
import com.utn.DentalOfficeManagement.Repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PlanRepository planRepository;
    private final PacienteMapper pacienteMapper;

    public PacienteService(PacienteRepository pacienteRepository, PlanRepository planRepository, PacienteMapper pacienteMapper) {
        this.pacienteRepository = pacienteRepository;
        this.planRepository = planRepository;
        this.pacienteMapper = pacienteMapper;
    }

    public PacienteResponseDTO crearPaciente(PacienteRequestDTO dto) {
        if (pacienteRepository.existsByDni(dto.getDni())) {
            throw new DuplicateResourceException("Paciente", "DNI", dto.getDni());
        }

        Plan plan = planRepository.findById(dto.getIdPlan().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Plan", "id", dto.getIdPlan()));

        Paciente paciente = pacienteMapper.requestDtoToEntity(dto);
        paciente.setPlan(plan);
        Paciente pacienteGuardado = pacienteRepository.save(paciente);
        return pacienteMapper.entityToResponseDto(pacienteGuardado);
    }

    @Transactional(readOnly = true)
    public PacienteResponseDTO obtenerPacientePorId(Integer id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", id));
        return pacienteMapper.entityToResponseDto(paciente);
    }

    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> buscarPorNombre(String nombre) {
        return pacienteRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(pacienteMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> buscarPorDni(String dni) {
        return pacienteRepository.findByDni(dni).stream()
                .map(pacienteMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PacienteResponseDTO> listarPacientesPorPlan(Integer idPlan) {
        return pacienteRepository.findByPlan_IdPlan(idPlan).stream()
                .map(pacienteMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public PacienteResponseDTO actualizarPaciente(Integer id, PacienteRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", id));

        // Verificar que el DNI sea único (si cambia)
        if (!paciente.getDni().equals(dto.getDni()) && pacienteRepository.existsByDni(dto.getDni())) {
            throw new DuplicateResourceException("Paciente", "DNI", dto.getDni());
        }

        Plan plan = planRepository.findById(dto.getIdPlan().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Plan", "id", dto.getIdPlan()));

        paciente.setNombre(dto.getNombre());
        paciente.setDni(dto.getDni());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setTelefono(dto.getTelefono());
        paciente.setNroAfiliado(dto.getNroAfiliado());
        paciente.setIsEmbarazada(dto.getIsEmbarazada());
        paciente.setPlan(plan);
        Paciente pacienteActualizado = pacienteRepository.save(paciente);
        return pacienteMapper.entityToResponseDto(pacienteActualizado);
    }

    public void eliminarPaciente(Integer id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente", "id", id);
        }
        pacienteRepository.deleteById(id);
    }
}

