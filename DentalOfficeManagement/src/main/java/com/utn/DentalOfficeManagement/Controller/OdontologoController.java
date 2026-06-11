package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.OdontologoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.OdontologoResponseDTO;
import com.utn.DentalOfficeManagement.Service.OdontologoService;
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
@RequestMapping("/api/v1/odontologos")
@Tag(name = "Odontólogos", description = "Gestión de odontólogos")
public class OdontologoController {

    private final OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo odontólogo", description = "Crea un nuevo odontólogo en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Odontólogo creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (Error de validación)")
    })
    public ResponseEntity<OdontologoResponseDTO> crearOdontologo(@Valid @RequestBody OdontologoRequestDTO dto) {
        OdontologoResponseDTO odontologo = odontologoService.crearOdontologo(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologo);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener odontólogo por ID", description = "Obtiene un odontólogo específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo encontrado"),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado con el ID provisto")
    })
    public ResponseEntity<OdontologoResponseDTO> obtenerOdontologoPorId(@PathVariable Long id) {
        OdontologoResponseDTO odontologo = odontologoService.obtenerOdontologoPorId(id);
        return ResponseEntity.ok(odontologo);
    }

    @GetMapping
    @Operation(summary = "Listar todos los odontólogos", description = "Obtiene la lista de todos los odontólogos registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de odontólogos obtenida con éxito")
    })
    public ResponseEntity<List<OdontologoResponseDTO>> listarTodos() {
        List<OdontologoResponseDTO> odontologos = odontologoService.listarTodos();
        return ResponseEntity.ok(odontologos);
    }

    @GetMapping("/buscar/nombre")
    @Operation(summary = "Buscar odontólogos por nombre", description = "Busca odontólogos que coincidan con el nombre especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de odontólogos obtenida con éxito")
    })
    public ResponseEntity<List<OdontologoResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<OdontologoResponseDTO> odontologos = odontologoService.buscarPorNombre(nombre);
        return ResponseEntity.ok(odontologos);
    }

    @GetMapping("/buscar/especialidad")
    @Operation(summary = "Buscar odontólogos por especialidad", description = "Busca odontólogos que coincidan con la especialidad especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de odontólogos obtenida con éxito")
    })
    public ResponseEntity<List<OdontologoResponseDTO>> buscarPorEspecialidad(@RequestParam String especialidad) {
        List<OdontologoResponseDTO> odontologos = odontologoService.buscarPorEspecialidad(especialidad);
        return ResponseEntity.ok(odontologos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un odontólogo", description = "Actualiza los datos de un odontólogo existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo actualizado con éxito"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (Error de validación)"),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado con el ID provisto")
    })
    public ResponseEntity<OdontologoResponseDTO> actualizarOdontologo(
            @PathVariable Long id,
            @Valid @RequestBody OdontologoRequestDTO dto) {
        OdontologoResponseDTO odontologo = odontologoService.actualizarOdontologo(id, dto);
        return ResponseEntity.ok(odontologo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un odontólogo", description = "Elimina un odontólogo del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Odontólogo eliminado con éxito (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado con el ID provisto")
    })
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable Long id) {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.noContent().build();
    }
}

