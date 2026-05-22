package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.PlanRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PlanResponseDTO;
import com.utn.DentalOfficeManagement.Exception.DuplicateResourceException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.PlanMapper;
import com.utn.DentalOfficeManagement.Model.ObraSocial;
import com.utn.DentalOfficeManagement.Model.Plan;
import com.utn.DentalOfficeManagement.Repository.ObraSocialRepository;
import com.utn.DentalOfficeManagement.Repository.PlanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanService {

    private final PlanRepository planRepository;
    private final ObraSocialRepository obraSocialRepository;
    private final PlanMapper planMapper;

    public PlanService(PlanRepository planRepository, ObraSocialRepository obraSocialRepository, PlanMapper planMapper) {
        this.planRepository = planRepository;
        this.obraSocialRepository = obraSocialRepository;
        this.planMapper = planMapper;
    }

    public PlanResponseDTO crearPlan(PlanRequestDTO dto) {
        ObraSocial obraSocial = obraSocialRepository.findById(dto.getObraSocialId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Obra Social", "id", dto.getObraSocialId()));

        if (planRepository.existsByNombreAndObraSocial_IdObraSocial(dto.getNombre(), obraSocial.getIdObraSocial())) {
            throw new DuplicateResourceException("Plan", "nombre en la obra social", dto.getNombre());
        }

        Plan plan = planMapper.requestDtoToEntity(dto);
        plan.setObraSocial(obraSocial);
        Plan planGuardado = planRepository.save(plan);
        return planMapper.entityToResponseDto(planGuardado);
    }

    @Transactional(readOnly = true)
    public PlanResponseDTO obtenerPlanPorId(Integer id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan", "id", id));
        return planMapper.entityToResponseDto(plan);
    }

    @Transactional(readOnly = true)
    public List<PlanResponseDTO> listarTodos() {
        return planRepository.findAll().stream()
                .map(planMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PlanResponseDTO> listarPlanesDeObraSocial(Integer idObraSocial) {
        return planRepository.findByObraSocial_IdObraSocial(idObraSocial).stream()
                .map(planMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public PlanResponseDTO actualizarPlan(Integer id, PlanRequestDTO dto) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan", "id", id));

        ObraSocial obraSocial = obraSocialRepository.findById(dto.getObraSocialId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Obra Social", "id", dto.getObraSocialId()));

        if (planRepository.existsByNombreAndObraSocial_IdObraSocial(dto.getNombre(), obraSocial.getIdObraSocial()) 
                && !plan.getIdPlan().equals(id)) {
            throw new DuplicateResourceException("Plan", "nombre en la obra social", dto.getNombre());
        }

        plan.setNombre(dto.getNombre());
        plan.setDescripcion(dto.getDescripcion());
        plan.setObraSocial(obraSocial);
        Plan planActualizado = planRepository.save(plan);
        return planMapper.entityToResponseDto(planActualizado);
    }

    public void eliminarPlan(Integer id) {
        if (!planRepository.existsById(id)) {
            throw new ResourceNotFoundException("Plan", "id", id);
        }
        planRepository.deleteById(id);
    }
}

