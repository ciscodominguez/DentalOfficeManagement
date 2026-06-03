package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.TurnoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.TurnoResponseDTO;
import com.utn.DentalOfficeManagement.Service.TurnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/turnos")
@Tag(name = "Turnos", description = "Gestión de turnos")
public class TurnoController {

    private final TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo turno", description = "Crea un nuevo turno en el sistema")
    public ResponseEntity<TurnoResponseDTO> crearTurno(@Valid @RequestBody TurnoRequestDTO dto) {
        TurnoResponseDTO turno = turnoService.crearTurno(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(turno);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener turno por ID", description = "Obtiene un turno específico por su ID")
    public ResponseEntity<TurnoResponseDTO> obtenerTurnoPorId(@PathVariable Long id) {
        TurnoResponseDTO turno = turnoService.obtenerTurnoPorId(id);
        return ResponseEntity.ok(turno);
    }

    @GetMapping
    @Operation(summary = "Listar todos los turnos", description = "Obtiene la lista de todos los turnos registrados")
    public ResponseEntity<List<TurnoResponseDTO>> listarTodos() {
        List<TurnoResponseDTO> turnos = turnoService.listarTodos();
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/paciente/{idPaciente}")
    @Operation(summary = "Listar turnos por paciente", description = "Obtiene todos los turnos de un paciente específico")
    public ResponseEntity<List<TurnoResponseDTO>> listarTurnosPorPaciente(@PathVariable Long idPaciente) {
        List<TurnoResponseDTO> turnos = turnoService.listarTurnosPorPaciente(idPaciente);
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/odontologo/{idOdontologo}")
    @Operation(summary = "Listar turnos por odontólogo", description = "Obtiene todos los turnos asignados a un odontólogo específico")
    public ResponseEntity<List<TurnoResponseDTO>> listarTurnosPorOdontologo(@PathVariable Long idOdontologo) {
        List<TurnoResponseDTO> turnos = turnoService.listarTurnosPorOdontologo(idOdontologo);
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/estado/{estado}")
    @Operation(summary = "Listar turnos por estado", description = "Obtiene todos los turnos en un estado específico")
    public ResponseEntity<List<TurnoResponseDTO>> listarTurnosPorEstado(@PathVariable String estado) {
        List<TurnoResponseDTO> turnos = turnoService.listarTurnosPorEstado(estado);
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/fecha/{fecha}")
    @Operation(summary = "Listar turnos por fecha", description = "Obtiene todos los turnos en una fecha específica")
    public ResponseEntity<List<TurnoResponseDTO>> listarTurnosPorFecha(@PathVariable LocalDate fecha) {
        List<TurnoResponseDTO> turnos = turnoService.listarTurnosPorFecha(fecha);
        return ResponseEntity.ok(turnos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un turno", description = "Actualiza los datos de un turno existente")
    public ResponseEntity<TurnoResponseDTO> actualizarTurno(
            @PathVariable Long id,
            @Valid @RequestBody TurnoRequestDTO dto) {
        TurnoResponseDTO turno = turnoService.actualizarTurno(id, dto);
        return ResponseEntity.ok(turno);
    }

    @PatchMapping("/{id}/estado")
    @Operation(summary = "Cambiar estado de un turno", description = "Cambia el estado de un turno específico")
    public ResponseEntity<TurnoResponseDTO> cambiarEstadoTurno(
            @PathVariable Long id,
            @RequestParam String estado) {
        TurnoResponseDTO turno = turnoService.cambiarEstadoTurno(id, estado);
        return ResponseEntity.ok(turno);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un turno", description = "Elimina un turno del sistema")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarTurno(id);
        return ResponseEntity.noContent().build();
    }
}

