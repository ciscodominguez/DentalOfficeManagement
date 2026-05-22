package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.FichaMedicaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.FichaMedicaResponseDTO;
import com.utn.DentalOfficeManagement.Service.FichaMedicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fichas-medicas")
@Tag(name = "Fichas Médicas", description = "Gestión de fichas médicas de pacientes")
public class FichaMedicaController {

    private final FichaMedicaService fichaMedicaService;

    public FichaMedicaController(FichaMedicaService fichaMedicaService) {
        this.fichaMedicaService = fichaMedicaService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva ficha médica", description = "Crea una nueva ficha médica para un paciente")
    public ResponseEntity<FichaMedicaResponseDTO> crearFichaMedica(@Valid @RequestBody FichaMedicaRequestDTO dto) {
        FichaMedicaResponseDTO fichaMedica = fichaMedicaService.crearFichaMedica(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaMedica);
    }

    @GetMapping("/paciente/{idPaciente}")
    @Operation(summary = "Obtener ficha médica de un paciente", description = "Obtiene la ficha médica de un paciente específico")
    public ResponseEntity<FichaMedicaResponseDTO> obtenerFichaMedicaPorPaciente(@PathVariable Integer idPaciente) {
        FichaMedicaResponseDTO fichaMedica = fichaMedicaService.obtenerFichaMedicaPorPaciente(idPaciente);
        return ResponseEntity.ok(fichaMedica);
    }

    @PutMapping("/paciente/{idPaciente}")
    @Operation(summary = "Actualizar ficha médica de un paciente", description = "Actualiza los datos de la ficha médica de un paciente")
    public ResponseEntity<FichaMedicaResponseDTO> actualizarFichaMedica(
            @PathVariable Integer idPaciente,
            @Valid @RequestBody FichaMedicaRequestDTO dto) {
        FichaMedicaResponseDTO fichaMedica = fichaMedicaService.actualizarFichaMedica(idPaciente, dto);
        return ResponseEntity.ok(fichaMedica);
    }

    @DeleteMapping("/paciente/{idPaciente}")
    @Operation(summary = "Eliminar ficha médica de un paciente", description = "Elimina la ficha médica de un paciente")
    public ResponseEntity<Void> eliminarFichaMedica(@PathVariable Integer idPaciente) {
        fichaMedicaService.eliminarFichaMedica(idPaciente);
        return ResponseEntity.noContent().build();
    }
}

