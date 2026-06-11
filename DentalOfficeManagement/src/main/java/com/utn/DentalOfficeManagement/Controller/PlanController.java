package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.PlanRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PlanResponseDTO;
import com.utn.DentalOfficeManagement.Service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Plan creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (Error de validación)"),
            @ApiResponse(responseCode = "409", description = "Conflict: Ya existe un plan con el mismo nombre para la obra social especificada")
    })
    public ResponseEntity<PlanResponseDTO> crearPlan(@Valid @RequestBody PlanRequestDTO dto) {
        PlanResponseDTO plan = planService.crearPlan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(plan);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener plan por ID", description = "Obtiene un plan específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan encontrado"),
            @ApiResponse(responseCode = "404", description = "Plan no encontrado con el ID provisto")
    })
    public ResponseEntity<PlanResponseDTO> obtenerPlanPorId(@PathVariable Long id) {
        PlanResponseDTO plan = planService.obtenerPlanPorId(id);
        return ResponseEntity.ok(plan);
    }

    @GetMapping
    @Operation(summary = "Listar todos los planes", description = "Obtiene la lista de todos los planes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de planes obtenida con éxito")
    })
    public ResponseEntity<List<PlanResponseDTO>> listarTodos() {
        List<PlanResponseDTO> planes = planService.listarTodos();
        return ResponseEntity.ok(planes);
    }

    @GetMapping("/obra-social/{idObraSocial}")
    @Operation(summary = "Listar planes por obra social", description = "Obtiene todos los planes de una obra social específica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de planes obtenida con éxito")
    })
    public ResponseEntity<List<PlanResponseDTO>> listarPlanesDeObraSocial(@PathVariable Long idObraSocial) {
        List<PlanResponseDTO> planes = planService.listarPlanesDeObraSocial(idObraSocial);
        return ResponseEntity.ok(planes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un plan", description = "Actualiza los datos de un plan existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Plan no encontrado con el ID provisto")
    })
    public ResponseEntity<PlanResponseDTO> actualizarPlan(
            @PathVariable Long id,
            @Valid @RequestBody PlanRequestDTO dto) {
        PlanResponseDTO plan = planService.actualizarPlan(id, dto);
        return ResponseEntity.ok(plan);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un plan", description = "Elimina un plan del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Plan eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Plan no encontrado con el ID provisto")
    })
    public ResponseEntity<Void> eliminarPlan(@PathVariable Long id) {
        planService.eliminarPlan(id);
        return ResponseEntity.noContent().build();
    }
}

