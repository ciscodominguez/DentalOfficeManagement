package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.PracticaRealizadaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PracticaRealizadaResponseDTO;
import com.utn.DentalOfficeManagement.Service.PracticaRealizadaService;
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
@RequestMapping("/api/v1/practicas-realizadas")
@Tag(name = "Prácticas Realizadas", description = "Gestión de prácticas realizadas en turnos")
public class PracticaRealizadaController {

    private final PracticaRealizadaService practicaRealizadaService;

    public PracticaRealizadaController(PracticaRealizadaService practicaRealizadaService) {
        this.practicaRealizadaService = practicaRealizadaService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva práctica realizada", description = "Registra una práctica ejecutada durante un turno")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Práctica realizada creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (Error de validación)")
    })
    public ResponseEntity<PracticaRealizadaResponseDTO> crearPracticaRealizada(@Valid @RequestBody PracticaRealizadaRequestDTO dto) {
        PracticaRealizadaResponseDTO practicaRealizada = practicaRealizadaService.crearPracticaRealizada(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(practicaRealizada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener práctica realizada por ID", description = "Obtiene una práctica realizada específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Práctica realizada encontrada"),
            @ApiResponse(responseCode = "404", description = "Práctica realizada no encontrada con el ID provisto")
    })
    public ResponseEntity<PracticaRealizadaResponseDTO> obtenerPracticaRealizadaPorId(@PathVariable Long id) {
        PracticaRealizadaResponseDTO practicaRealizada = practicaRealizadaService.obtenerPracticaRealizadaPorId(id);
        return ResponseEntity.ok(practicaRealizada);
    }

    @GetMapping
    @Operation(summary = "Listar todas las prácticas realizadas", description = "Obtiene la lista de todas las prácticas realizadas registradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de prácticas realizadas obtenida con éxito")
    })
    public ResponseEntity<List<PracticaRealizadaResponseDTO>> listarTodas() {
        List<PracticaRealizadaResponseDTO> practicasRealizadas = practicaRealizadaService.listarTodas();
        return ResponseEntity.ok(practicasRealizadas);
    }

    @GetMapping("/turno/{idTurno}")
    @Operation(summary = "Listar prácticas realizadas por turno", description = "Obtiene todas las prácticas realizadas en un turno específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de prácticas realizadas por turno obtenida con éxito"),
            @ApiResponse(responseCode = "404", description = "Turno no encontrado con el ID provisto")
    })
    public ResponseEntity<List<PracticaRealizadaResponseDTO>> listarPracticasRealizadasPorTurno(@PathVariable Long idTurno) {
        List<PracticaRealizadaResponseDTO> practicasRealizadas = practicaRealizadaService.listarPracticasRealizadasPorTurno(idTurno);
        return ResponseEntity.ok(practicasRealizadas);
    }

    @GetMapping("/paciente/{idPaciente}")
    @Operation(summary = "Listar prácticas realizadas por paciente", description = "Obtiene todas las prácticas realizadas a un paciente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de prácticas realizadas por paciente obtenida con éxito"),
            @ApiResponse(responseCode = "404", description = "Paciente no encontrado con el ID provisto")
    })
    public ResponseEntity<List<PracticaRealizadaResponseDTO>> listarPracticasRealizadasPorPaciente(@PathVariable Long idPaciente) {
        List<PracticaRealizadaResponseDTO> practicasRealizadas = practicaRealizadaService.listarPracticasRealizadasPorPaciente(idPaciente);
        return ResponseEntity.ok(practicasRealizadas);
    }

    @GetMapping("/sin-pago")
    @Operation(summary = "Listar prácticas realizadas sin pago", description = "Obtiene todas las prácticas realizadas que aún no han sido pagadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de prácticas realizadas sin pago obtenida con éxito")
    })
    public ResponseEntity<List<PracticaRealizadaResponseDTO>> listarPracticasRealizadasSinPago() {
        List<PracticaRealizadaResponseDTO> practicasRealizadas = practicaRealizadaService.listarPracticasRealizadasSinPago();
        return ResponseEntity.ok(practicasRealizadas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una práctica realizada", description = "Actualiza los datos de una práctica realizada existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Práctica realizada actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Práctica realizada no encontrada con el ID provisto")
    })
    public ResponseEntity<PracticaRealizadaResponseDTO> actualizarPracticaRealizada(
            @PathVariable Long id,
            @Valid @RequestBody PracticaRealizadaRequestDTO dto) {
        PracticaRealizadaResponseDTO practicaRealizada = practicaRealizadaService.actualizarPracticaRealizada(id, dto);
        return ResponseEntity.ok(practicaRealizada);
    }

    @PatchMapping("/{id}/asignar-pago/{idPago}")
    @Operation(summary = "Asignar pago a práctica realizada", description = "Asigna un pago a una práctica realizada sin pago")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pago asignado con éxito"),
            @ApiResponse(responseCode = "404", description = "Práctica realizada no encontrada con el ID provisto")
    })
    public ResponseEntity<PracticaRealizadaResponseDTO> asignarPago(
            @PathVariable Long id,
            @PathVariable Long idPago) {
        PracticaRealizadaResponseDTO practicaRealizada = practicaRealizadaService.asignarPago(id, idPago);
        return ResponseEntity.ok(practicaRealizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una práctica realizada", description = "Elimina una práctica realizada del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Práctica realizada eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Práctica realizada no encontrada con el ID provisto")
    })
    public ResponseEntity<Void> eliminarPracticaRealizada(@PathVariable Long id) {
        practicaRealizadaService.eliminarPracticaRealizada(id);
        return ResponseEntity.noContent().build();
    }
}

