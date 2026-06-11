package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.PiezaDentalRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PiezaDentalResponseDTO;
import com.utn.DentalOfficeManagement.Service.PiezaDentalService;
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
@RequestMapping("/api/v1/piezas-dentales")
@Tag(name = "Piezas Dentales", description = "Gestión de piezas dentales")
public class PiezaDentalController {

    private final PiezaDentalService piezaDentalService;

    public PiezaDentalController(PiezaDentalService piezaDentalService) {
        this.piezaDentalService = piezaDentalService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva pieza dental", description = "Crea un nuevo registro de pieza dental en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pieza dental creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (Error de validación)")
    })
    public ResponseEntity<PiezaDentalResponseDTO> crearPiezaDental(@Valid @RequestBody PiezaDentalRequestDTO dto) {
        PiezaDentalResponseDTO piezaDental = piezaDentalService.crearPiezaDental(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(piezaDental);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pieza dental por ID", description = "Obtiene una pieza dental específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pieza dental encontrada"),
            @ApiResponse(responseCode = "404", description = "Pieza dental no encontrada con el ID provisto")
    })
    public ResponseEntity<PiezaDentalResponseDTO> obtenerPiezaDentalPorId(@PathVariable Long id) {
        PiezaDentalResponseDTO piezaDental = piezaDentalService.obtenerPiezaDentalPorId(id);
        return ResponseEntity.ok(piezaDental);
    }

    @GetMapping
    @Operation(summary = "Listar todas las piezas dentales", description = "Obtiene la lista de todas las piezas dentales registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de piezas dentales obtenida con éxito")
    })
    public ResponseEntity<List<PiezaDentalResponseDTO>> listarTodas() {
        List<PiezaDentalResponseDTO> piezasDentales = piezaDentalService.listarTodas();
        return ResponseEntity.ok(piezasDentales);
    }

    @GetMapping("/paciente/{idPaciente}")
    @Operation(summary = "Listar piezas dentales por paciente", description = "Obtiene todas las piezas dentales de un paciente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de piezas dentales obtenida con éxito")
    })
    public ResponseEntity<List<PiezaDentalResponseDTO>> listarPiezasDentalesdePaciente(@PathVariable Long idPaciente) {
        List<PiezaDentalResponseDTO> piezasDentales = piezaDentalService.listarPiezasDentalesdePaciente(idPaciente);
        return ResponseEntity.ok(piezasDentales);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una pieza dental", description = "Actualiza los datos de una pieza dental existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pieza dental actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Pieza dental no encontrada con el ID provisto")
    })
    public ResponseEntity<PiezaDentalResponseDTO> actualizarPiezaDental(
            @PathVariable Long id,
            @Valid @RequestBody PiezaDentalRequestDTO dto) {
        PiezaDentalResponseDTO piezaDental = piezaDentalService.actualizarPiezaDental(id, dto);
        return ResponseEntity.ok(piezaDental);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una pieza dental", description = "Elimina una pieza dental del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pieza dental eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Pieza dental no encontrada con el ID provisto")
    })
    public ResponseEntity<Void> eliminarPiezaDental(@PathVariable Long id) {
        piezaDentalService.eliminarPiezaDental(id);
        return ResponseEntity.noContent().build();
    }
}

