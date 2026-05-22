package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.PracticaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PracticaResponseDTO;
import com.utn.DentalOfficeManagement.Service.PracticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/practicas")
@Tag(name = "Prácticas", description = "Gestión de prácticas odontológicas")
public class PracticaController {

    private final PracticaService practicaService;

    public PracticaController(PracticaService practicaService) {
        this.practicaService = practicaService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva práctica", description = "Crea una nueva práctica odontológica en el sistema")
    public ResponseEntity<PracticaResponseDTO> crearPractica(@Valid @RequestBody PracticaRequestDTO dto) {
        PracticaResponseDTO practica = practicaService.crearPractica(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(practica);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener práctica por ID", description = "Obtiene una práctica específica por su ID")
    public ResponseEntity<PracticaResponseDTO> obtenerPracticaPorId(@PathVariable Integer id) {
        PracticaResponseDTO practica = practicaService.obtenerPracticaPorId(id);
        return ResponseEntity.ok(practica);
    }

    @GetMapping
    @Operation(summary = "Listar todas las prácticas", description = "Obtiene la lista de todas las prácticas registradas")
    public ResponseEntity<List<PracticaResponseDTO>> listarTodas() {
        List<PracticaResponseDTO> practicas = practicaService.listarTodas();
        return ResponseEntity.ok(practicas);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar prácticas por nombre", description = "Busca prácticas que coincidan con el nombre especificado")
    public ResponseEntity<List<PracticaResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<PracticaResponseDTO> practicas = practicaService.buscarPorNombre(nombre);
        return ResponseEntity.ok(practicas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una práctica", description = "Actualiza los datos de una práctica existente")
    public ResponseEntity<PracticaResponseDTO> actualizarPractica(
            @PathVariable Integer id,
            @Valid @RequestBody PracticaRequestDTO dto) {
        PracticaResponseDTO practica = practicaService.actualizarPractica(id, dto);
        return ResponseEntity.ok(practica);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una práctica", description = "Elimina una práctica del sistema")
    public ResponseEntity<Void> eliminarPractica(@PathVariable Integer id) {
        practicaService.eliminarPractica(id);
        return ResponseEntity.noContent().build();
    }
}

