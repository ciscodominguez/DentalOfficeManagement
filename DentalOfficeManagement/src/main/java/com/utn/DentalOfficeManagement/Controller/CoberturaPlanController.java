package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.CoberturaPlanRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.CoberturaPlanResponseDTO;
import com.utn.DentalOfficeManagement.Service.CoberturaPlanService;
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
@RequestMapping("/api/v1/coberturas")
@Tag(name = "Coberturas", description = "Gestión de coberturas de planes")
public class CoberturaPlanController {

    private final CoberturaPlanService coberturaPlanService;

    public CoberturaPlanController(CoberturaPlanService coberturaPlanService) {
        this.coberturaPlanService = coberturaPlanService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva cobertura", description = "Crea una nueva cobertura para un plan sobre una práctica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cobertura creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (Error de validación)"),
            @ApiResponse(responseCode = "409", description = "Conflict: La cobertura para este plan y práctica ya existe")
    })
    public ResponseEntity<CoberturaPlanResponseDTO> crearCobertura(@Valid @RequestBody CoberturaPlanRequestDTO dto) {
        CoberturaPlanResponseDTO cobertura = coberturaPlanService.crearCobertura(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cobertura);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cobertura por ID", description = "Obtiene una cobertura específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cobertura encontrada"),
            @ApiResponse(responseCode = "404", description = "Cobertura no encontrada con el ID provisto")
    })
    public ResponseEntity<CoberturaPlanResponseDTO> obtenerCoberturaPorId(@PathVariable Long id) {
        CoberturaPlanResponseDTO cobertura = coberturaPlanService.obtenerCoberturaPorId(id);
        return ResponseEntity.ok(cobertura);
    }

    @GetMapping
    @Operation(summary = "Listar todas las coberturas", description = "Obtiene la lista de todas las coberturas registradas")
    @ApiResponse(responseCode = "200", description = "Lista de coberturas obtenida con éxito")
    public ResponseEntity<List<CoberturaPlanResponseDTO>> listarTodas() {
        List<CoberturaPlanResponseDTO> coberturas = coberturaPlanService.listarTodas();
        return ResponseEntity.ok(coberturas);
    }

    @GetMapping("/plan/{idPlan}")
    @Operation(summary = "Listar coberturas de un plan", description = "Obtiene todas las coberturas de un plan específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de coberturas del plan obtenida con éxito"),
            @ApiResponse(responseCode = "404", description = "El ID del plan especificado no existe")
    })
    public ResponseEntity<List<CoberturaPlanResponseDTO>> listarCoberturasDeUnPlan(@PathVariable Long idPlan) {
        List<CoberturaPlanResponseDTO> coberturas = coberturaPlanService.listarCoberturasDeUnPlan(idPlan);
        return ResponseEntity.ok(coberturas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cobertura", description = "Actualiza los datos de una cobertura existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cobertura actualizada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos"),
            @ApiResponse(responseCode = "404", description = "Cobertura no encontrada con el ID provisto")
    })
    public ResponseEntity<CoberturaPlanResponseDTO> actualizarCobertura(
            @PathVariable Long id,
            @Valid @RequestBody CoberturaPlanRequestDTO dto) {
        CoberturaPlanResponseDTO cobertura = coberturaPlanService.actualizarCobertura(id, dto);
        return ResponseEntity.ok(cobertura);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cobertura", description = "Elimina una cobertura del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cobertura eliminada con éxito (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Cobertura no encontrada con el ID provisto")
    })
    public ResponseEntity<Void> eliminarCobertura(@PathVariable Long id) {
        coberturaPlanService.eliminarCobertura(id);
        return ResponseEntity.noContent().build();
    }
}

