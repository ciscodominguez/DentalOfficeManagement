package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.PlanRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PlanResponseDTO;
import com.utn.DentalOfficeManagement.Service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/planes")
@Tag(name = "Planes", description = "Gestión de planes")
public class PlanController {

    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo plan", description = "Crea un nuevo plan en el sistema")
    public ResponseEntity<PlanResponseDTO> crearPlan(@Valid @RequestBody PlanRequestDTO dto) {
        PlanResponseDTO plan = planService.crearPlan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(plan);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener plan por ID", description = "Obtiene un plan específico por su ID")
    public ResponseEntity<PlanResponseDTO> obtenerPlanPorId(@PathVariable Long id) {
        PlanResponseDTO plan = planService.obtenerPlanPorId(id);
        return ResponseEntity.ok(plan);
    }

    @GetMapping
    @Operation(summary = "Listar todos los planes", description = "Obtiene la lista de todos los planes registrados")
    public ResponseEntity<List<PlanResponseDTO>> listarTodos() {
        List<PlanResponseDTO> planes = planService.listarTodos();
        return ResponseEntity.ok(planes);
    }

    @GetMapping("/obra-social/{idObraSocial}")
    @Operation(summary = "Listar planes por obra social", description = "Obtiene todos los planes de una obra social específica")
    public ResponseEntity<List<PlanResponseDTO>> listarPlanesDeObraSocial(@PathVariable Long idObraSocial) {
        List<PlanResponseDTO> planes = planService.listarPlanesDeObraSocial(idObraSocial);
        return ResponseEntity.ok(planes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un plan", description = "Actualiza los datos de un plan existente")
    public ResponseEntity<PlanResponseDTO> actualizarPlan(
            @PathVariable Long id,
            @Valid @RequestBody PlanRequestDTO dto) {
        PlanResponseDTO plan = planService.actualizarPlan(id, dto);
        return ResponseEntity.ok(plan);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un plan", description = "Elimina un plan del sistema")
    public ResponseEntity<Void> eliminarPlan(@PathVariable Long id) {
        planService.eliminarPlan(id);
        return ResponseEntity.noContent().build();
    }
}

