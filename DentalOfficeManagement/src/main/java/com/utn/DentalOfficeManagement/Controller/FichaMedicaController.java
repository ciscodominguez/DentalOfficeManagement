package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.FichaMedicaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.FichaMedicaResponseDTO;
import com.utn.DentalOfficeManagement.Service.FichaMedicaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ficha médica creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (Error de validación)"),
            @ApiResponse(responseCode = "409", description = "Conflict: El paciente ya posee una ficha médica registrada")
    })
    public ResponseEntity<FichaMedicaResponseDTO> crearFichaMedica(@Valid @RequestBody FichaMedicaRequestDTO dto) {
        FichaMedicaResponseDTO fichaMedica = fichaMedicaService.crearFichaMedica(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(fichaMedica);
    }

    @GetMapping("/paciente/{idPaciente}")
    @Operation(summary = "Obtener ficha médica de un paciente", description = "Obtiene la ficha médica de un paciente específico")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Ficha médica encontrada"),
            @ApiResponse(responseCode = "404", description = "Ficha médica no encontrada para el paciente con el ID provisto")
    })
    public ResponseEntity<FichaMedicaResponseDTO> obtenerFichaMedicaPorPaciente(@PathVariable Long idPaciente) {
        FichaMedicaResponseDTO fichaMedica = fichaMedicaService.obtenerFichaMedicaPorPaciente(idPaciente);
        return ResponseEntity.ok(fichaMedica);
    }

    @PutMapping("/paciente/{idPaciente}")
    @Operation(summary = "Actualizar ficha médica de un paciente", description = "Actualiza los datos de la ficha médica de un paciente")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200", description = "Ficha médica actualizada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de actualización inválidos"),
            @ApiResponse(responseCode = "404", description = "Ficha médica no encontrada para el paciente con el ID provisto")
    })
    public ResponseEntity<FichaMedicaResponseDTO> actualizarFichaMedica(
            @PathVariable Long idPaciente,
            @Valid @RequestBody FichaMedicaRequestDTO dto) {
        FichaMedicaResponseDTO fichaMedica = fichaMedicaService.actualizarFichaMedica(idPaciente, dto);
        return ResponseEntity.ok(fichaMedica);
    }

    @DeleteMapping("/paciente/{idPaciente}")
    @Operation(summary = "Eliminar ficha médica de un paciente", description = "Elimina la ficha médica de un paciente")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "204", description = "Ficha médica eliminada con éxito (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Ficha médica no encontrada para el paciente con el ID provisto")
    })
    public ResponseEntity<Void> eliminarFichaMedica(@PathVariable Long idPaciente) {
        fichaMedicaService.eliminarFichaMedica(idPaciente);
        return ResponseEntity.noContent().build();
    }
}

