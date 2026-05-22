package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.CoberturaPlanRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.CoberturaPlanResponseDTO;
import com.utn.DentalOfficeManagement.Exception.DuplicateResourceException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.CoberturaPlanMapper;
import com.utn.DentalOfficeManagement.Model.CoberturaPlan;
import com.utn.DentalOfficeManagement.Model.Plan;
import com.utn.DentalOfficeManagement.Model.Practica;
import com.utn.DentalOfficeManagement.Repository.CoberturaPlanRepository;
import com.utn.DentalOfficeManagement.Repository.PlanRepository;
import com.utn.DentalOfficeManagement.Repository.PracticaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CoberturaPlanService {

    private final CoberturaPlanRepository coberturaPlanRepository;
    private final PlanRepository planRepository;
    private final PracticaRepository practicaRepository;
    private final CoberturaPlanMapper coberturaPlanMapper;

    public CoberturaPlanService(CoberturaPlanRepository coberturaPlanRepository, PlanRepository planRepository, PracticaRepository practicaRepository, CoberturaPlanMapper coberturaPlanMapper) {
        this.coberturaPlanRepository = coberturaPlanRepository;
        this.planRepository = planRepository;
        this.practicaRepository = practicaRepository;
        this.coberturaPlanMapper = coberturaPlanMapper;
    }

    public CoberturaPlanResponseDTO crearCobertura(CoberturaPlanRequestDTO dto) {
        Plan plan = planRepository.findById(dto.getPlanId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Plan", "id", dto.getPlanId()));

        Practica practica = practicaRepository.findById(dto.getPracticaId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Práctica", "id", dto.getPracticaId()));

        if (coberturaPlanRepository.existsByPlan_IdPlanAndPractica_IdPractica(plan.getIdPlan(), practica.getIdPractica())) {
            throw new DuplicateResourceException("Cobertura", "combinación de plan y práctica", dto.getPlanId() + "-" + dto.getPracticaId());
        }

        CoberturaPlan cobertura = coberturaPlanMapper.requestDtoToEntity(dto);
        cobertura.setPlan(plan);
        cobertura.setPractica(practica);
        CoberturaPlan coberturaGuardada = coberturaPlanRepository.save(cobertura);
        return coberturaPlanMapper.entityToResponseDto(coberturaGuardada);
    }

    @Transactional(readOnly = true)
    public CoberturaPlanResponseDTO obtenerCoberturaPorId(Integer id) {
        CoberturaPlan cobertura = coberturaPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cobertura", "id", id));
        return coberturaPlanMapper.entityToResponseDto(cobertura);
    }

    @Transactional(readOnly = true)
    public List<CoberturaPlanResponseDTO> listarTodas() {
        return coberturaPlanRepository.findAll().stream()
                .map(coberturaPlanMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CoberturaPlanResponseDTO> listarCoberturasDeUnPlan(Integer idPlan) {
        return coberturaPlanRepository.findByPlan_IdPlan(idPlan).stream()
                .map(coberturaPlanMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public CoberturaPlanResponseDTO actualizarCobertura(Integer id, CoberturaPlanRequestDTO dto) {
        CoberturaPlan cobertura = coberturaPlanRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cobertura", "id", id));

        Plan plan = planRepository.findById(dto.getPlanId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Plan", "id", dto.getPlanId()));

        Practica practica = practicaRepository.findById(dto.getPracticaId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Práctica", "id", dto.getPracticaId()));

        if (coberturaPlanRepository.existsByPlan_IdPlanAndPractica_IdPracticaAndIdCoberturaPlanNot(plan.getIdPlan(), practica.getIdPractica(), id)) {
            throw new DuplicateResourceException("Cobertura", "combinación de plan y práctica", dto.getPlanId() + "-" + dto.getPracticaId());
        }

        cobertura.setPlan(plan);
        cobertura.setPractica(practica);
        cobertura.setPorcentajeCobertura(dto.getPorcentajeCobertura());
        cobertura.setRequiereAutorizacion(dto.getRequiereAutorizacion());
        CoberturaPlan coberturaActualizada = coberturaPlanRepository.save(cobertura);
        return coberturaPlanMapper.entityToResponseDto(coberturaActualizada);
    }

    public void eliminarCobertura(Integer id) {
        if (!coberturaPlanRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cobertura", "id", id);
        }
        coberturaPlanRepository.deleteById(id);
    }
}

