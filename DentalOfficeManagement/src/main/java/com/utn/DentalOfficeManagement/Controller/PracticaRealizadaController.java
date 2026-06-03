package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.PracticaRealizadaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PracticaRealizadaResponseDTO;
import com.utn.DentalOfficeManagement.Service.PracticaRealizadaService;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity<PracticaRealizadaResponseDTO> crearPracticaRealizada(@Valid @RequestBody PracticaRealizadaRequestDTO dto) {
        PracticaRealizadaResponseDTO practicaRealizada = practicaRealizadaService.crearPracticaRealizada(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(practicaRealizada);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener práctica realizada por ID", description = "Obtiene una práctica realizada específica por su ID")
    public ResponseEntity<PracticaRealizadaResponseDTO> obtenerPracticaRealizadaPorId(@PathVariable Long id) {
        PracticaRealizadaResponseDTO practicaRealizada = practicaRealizadaService.obtenerPracticaRealizadaPorId(id);
        return ResponseEntity.ok(practicaRealizada);
    }

    @GetMapping
    @Operation(summary = "Listar todas las prácticas realizadas", description = "Obtiene la lista de todas las prácticas realizadas registradas")
    public ResponseEntity<List<PracticaRealizadaResponseDTO>> listarTodas() {
        List<PracticaRealizadaResponseDTO> practicasRealizadas = practicaRealizadaService.listarTodas();
        return ResponseEntity.ok(practicasRealizadas);
    }

    @GetMapping("/turno/{idTurno}")
    @Operation(summary = "Listar prácticas realizadas por turno", description = "Obtiene todas las prácticas realizadas en un turno específico")
    public ResponseEntity<List<PracticaRealizadaResponseDTO>> listarPracticasRealizadasPorTurno(@PathVariable Long idTurno) {
        List<PracticaRealizadaResponseDTO> practicasRealizadas = practicaRealizadaService.listarPracticasRealizadasPorTurno(idTurno);
        return ResponseEntity.ok(practicasRealizadas);
    }

    @GetMapping("/paciente/{idPaciente}")
    @Operation(summary = "Listar prácticas realizadas por paciente", description = "Obtiene todas las prácticas realizadas a un paciente específico")
    public ResponseEntity<List<PracticaRealizadaResponseDTO>> listarPracticasRealizadasPorPaciente(@PathVariable Long idPaciente) {
        List<PracticaRealizadaResponseDTO> practicasRealizadas = practicaRealizadaService.listarPracticasRealizadasPorPaciente(idPaciente);
        return ResponseEntity.ok(practicasRealizadas);
    }

    @GetMapping("/sin-pago")
    @Operation(summary = "Listar prácticas realizadas sin pago", description = "Obtiene todas las prácticas realizadas que aún no han sido pagadas")
    public ResponseEntity<List<PracticaRealizadaResponseDTO>> listarPracticasRealizadasSinPago() {
        List<PracticaRealizadaResponseDTO> practicasRealizadas = practicaRealizadaService.listarPracticasRealizadasSinPago();
        return ResponseEntity.ok(practicasRealizadas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una práctica realizada", description = "Actualiza los datos de una práctica realizada existente")
    public ResponseEntity<PracticaRealizadaResponseDTO> actualizarPracticaRealizada(
            @PathVariable Long id,
            @Valid @RequestBody PracticaRealizadaRequestDTO dto) {
        PracticaRealizadaResponseDTO practicaRealizada = practicaRealizadaService.actualizarPracticaRealizada(id, dto);
        return ResponseEntity.ok(practicaRealizada);
    }

    @PatchMapping("/{id}/asignar-pago/{idPago}")
    @Operation(summary = "Asignar pago a práctica realizada", description = "Asigna un pago a una práctica realizada sin pago")
    public ResponseEntity<PracticaRealizadaResponseDTO> asignarPago(
            @PathVariable Long id,
            @PathVariable Long idPago) {
        PracticaRealizadaResponseDTO practicaRealizada = practicaRealizadaService.asignarPago(id, idPago);
        return ResponseEntity.ok(practicaRealizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una práctica realizada", description = "Elimina una práctica realizada del sistema")
    public ResponseEntity<Void> eliminarPracticaRealizada(@PathVariable Long id) {
        practicaRealizadaService.eliminarPracticaRealizada(id);
        return ResponseEntity.noContent().build();
    }
}

