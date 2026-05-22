package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.PacienteRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PacienteResponseDTO;
import com.utn.DentalOfficeManagement.Service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pacientes")
@Tag(name = "Pacientes", description = "Gestión de pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo paciente", description = "Crea un nuevo paciente en el sistema")
    public ResponseEntity<PacienteResponseDTO> crearPaciente(@Valid @RequestBody PacienteRequestDTO dto) {
        PacienteResponseDTO paciente = pacienteService.crearPaciente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(paciente);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener paciente por ID", description = "Obtiene un paciente específico por su ID")
    public ResponseEntity<PacienteResponseDTO> obtenerPacientePorId(@PathVariable Integer id) {
        PacienteResponseDTO paciente = pacienteService.obtenerPacientePorId(id);
        return ResponseEntity.ok(paciente);
    }

    @GetMapping
    @Operation(summary = "Listar todos los pacientes", description = "Obtiene la lista de todos los pacientes registrados")
    public ResponseEntity<List<PacienteResponseDTO>> listarTodos() {
        List<PacienteResponseDTO> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/buscar/nombre")
    @Operation(summary = "Buscar pacientes por nombre", description = "Busca pacientes que coincidan con el nombre especificado")
    public ResponseEntity<List<PacienteResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<PacienteResponseDTO> pacientes = pacienteService.buscarPorNombre(nombre);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/buscar/dni")
    @Operation(summary = "Buscar pacientes por DNI", description = "Busca pacientes que coincidan con el DNI especificado")
    public ResponseEntity<List<PacienteResponseDTO>> buscarPorDni(@RequestParam String dni) {
        List<PacienteResponseDTO> pacientes = pacienteService.buscarPorDni(dni);
        return ResponseEntity.ok(pacientes);
    }

    @GetMapping("/plan/{idPlan}")
    @Operation(summary = "Listar pacientes por plan", description = "Obtiene todos los pacientes asociados a un plan específico")
    public ResponseEntity<List<PacienteResponseDTO>> listarPacientesPorPlan(@PathVariable Integer idPlan) {
        List<PacienteResponseDTO> pacientes = pacienteService.listarPacientesPorPlan(idPlan);
        return ResponseEntity.ok(pacientes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un paciente", description = "Actualiza los datos de un paciente existente")
    public ResponseEntity<PacienteResponseDTO> actualizarPaciente(
            @PathVariable Integer id,
            @Valid @RequestBody PacienteRequestDTO dto) {
        PacienteResponseDTO paciente = pacienteService.actualizarPaciente(id, dto);
        return ResponseEntity.ok(paciente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un paciente", description = "Elimina un paciente del sistema")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Integer id) {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.noContent().build();
    }
}

