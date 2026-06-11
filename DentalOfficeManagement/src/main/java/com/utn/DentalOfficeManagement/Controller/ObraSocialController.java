package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.ObraSocialRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.ObraSocialResponseDTO;
import com.utn.DentalOfficeManagement.Service.ObraSocialService;
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
@RequestMapping("/api/v1/obras-sociales")
@Tag(name = "Obras Sociales", description = "Gestión de obras sociales")
public class ObraSocialController {

    private final ObraSocialService obraSocialService;

    public ObraSocialController(ObraSocialService obraSocialService) {
        this.obraSocialService = obraSocialService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva obra social", description = "Crea una nueva obra social en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Obra social creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (Error de validación)"),
            @ApiResponse(responseCode = "409", description = "Conflict: Ya existe una obra social con el mismo nombre")
    })
    public ResponseEntity<ObraSocialResponseDTO> crearObraSocial(@Valid @RequestBody ObraSocialRequestDTO dto) {
        ObraSocialResponseDTO obraSocial = obraSocialService.crearObraSocial(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(obraSocial);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener obra social por ID", description = "Obtiene una obra social específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obra social encontrada"),
            @ApiResponse(responseCode = "404", description = "Obra social no encontrada con el ID provisto")
    })
    public ResponseEntity<ObraSocialResponseDTO> obtenerObraSocialPorId(@PathVariable Long id) {
        ObraSocialResponseDTO obraSocial = obraSocialService.obtenerObraSocialPorId(id);
        return ResponseEntity.ok(obraSocial);
    }

    @GetMapping
    @Operation(summary = "Listar todas las obras sociales", description = "Obtiene la lista de todas las obras sociales registradas")
   @ApiResponse(responseCode = "200", description = "Lista de obras sociales obtenida con éxito")
    public ResponseEntity<List<ObraSocialResponseDTO>> listarTodas() {
        List<ObraSocialResponseDTO> obrasSociales = obraSocialService.listarTodas();
        return ResponseEntity.ok(obrasSociales);
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar obras sociales por nombre", description = "Busca obras sociales que coincidan con el nombre especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de obras sociales obtenida con éxito"),
            @ApiResponse(responseCode = "400", description = "Parámetro de búsqueda inválido (Error de validación)")
    })
    public ResponseEntity<List<ObraSocialResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<ObraSocialResponseDTO> obrasSociales = obraSocialService.buscarPorNombre(nombre);
        return ResponseEntity.ok(obrasSociales);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar una obra social", description = "Actualiza los datos de una obra social existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Obra social actualizada con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (Error de validación)"),
            @ApiResponse(responseCode = "404", description = "Obra social no encontrada con el ID provisto")
    })
    public ResponseEntity<ObraSocialResponseDTO> actualizarObraSocial(
            @PathVariable Long id,
            @Valid @RequestBody ObraSocialRequestDTO dto) {
        ObraSocialResponseDTO obraSocial = obraSocialService.actualizarObraSocial(id, dto);
        return ResponseEntity.ok(obraSocial);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar una obra social", description = "Elimina una obra social del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Obra social eliminada con éxito (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Obra social no encontrada con el ID provisto")
    })
    public ResponseEntity<Void> eliminarObraSocial(@PathVariable Long id) {
        obraSocialService.eliminarObraSocial(id);
        return ResponseEntity.noContent().build();
    }
}

