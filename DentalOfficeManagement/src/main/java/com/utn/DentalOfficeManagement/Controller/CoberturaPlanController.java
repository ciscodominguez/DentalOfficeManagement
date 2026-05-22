package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.CoberturaPlanRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.CoberturaPlanResponseDTO;
import com.utn.DentalOfficeManagement.Service.CoberturaPlanService;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<CoberturaPlanResponseDTO> crearCobertura(@Valid @RequestBody CoberturaPlanRequestDTO dto) {
        CoberturaPlanResponseDTO cobertura = coberturaPlanService.crearCobertura(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cobertura);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cobertura por ID", description = "Obtiene una cobertura específica por su ID")
    public ResponseEntity<CoberturaPlanResponseDTO> obtenerCoberturaPorId(@PathVariable Integer id) {
        CoberturaPlanResponseDTO cobertura = coberturaPlanService.obtenerCoberturaPorId(id);
        return ResponseEntity.ok(cobertura);
    }

    @GetMapping
    @Operation(summary = "Listar todas las coberturas", description = "Obtiene la lista de todas las coberturas registradas")
    public ResponseEntity<List<CoberturaPlanResponseDTO>> listarTodas() {
        List<CoberturaPlanResponseDTO> coberturas = coberturaPlanService.listarTodas();
        return ResponseEntity.ok(coberturas);
    }

    @GetMapping("/plan/{idPlan}")
    @Operation(summary = "Listar coberturas de un plan", description = "Obtiene todas las coberturas de un plan específico")
    public ResponseEntity<List<CoberturaPlanResponseDTO>> listarCoberturasDeUnPlan(@PathVariable Integer idPlan) {
        List<CoberturaPlanResponseDTO> coberturas = coberturaPlanService.listarCoberturasDeUnPlan(idPlan);
        return ResponseEntity.ok(coberturas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una cobertura", description = "Actualiza los datos de una cobertura existente")
    public ResponseEntity<CoberturaPlanResponseDTO> actualizarCobertura(
            @PathVariable Integer id,
            @Valid @RequestBody CoberturaPlanRequestDTO dto) {
        CoberturaPlanResponseDTO cobertura = coberturaPlanService.actualizarCobertura(id, dto);
        return ResponseEntity.ok(cobertura);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una cobertura", description = "Elimina una cobertura del sistema")
    public ResponseEntity<Void> eliminarCobertura(@PathVariable Integer id) {
        coberturaPlanService.eliminarCobertura(id);
        return ResponseEntity.noContent().build();
    }
}

