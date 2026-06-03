package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.NomencladorPracticaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.NomencladorPracticaResponseDTO;
import com.utn.DentalOfficeManagement.Service.NomencladorPracticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nomencladoras")
@Tag(name = "Nomencladoras", description = "Gestión de nomencladoras de prácticas")
public class NomencladorPracticaController {

    private final NomencladorPracticaService nomencladorPracticaService;

    public NomencladorPracticaController(NomencladorPracticaService nomencladorPracticaService) {
        this.nomencladorPracticaService = nomencladorPracticaService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo nomenclador", description = "Crea una nueva asociación de práctica con código externo en una obra social")
    public ResponseEntity<NomencladorPracticaResponseDTO> crearNomenclador(@Valid @RequestBody NomencladorPracticaRequestDTO dto) {
        NomencladorPracticaResponseDTO nomenclador = nomencladorPracticaService.crearNomenclador(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nomenclador);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener nomenclador por ID", description = "Obtiene un nomenclador específico por su ID")
    public ResponseEntity<NomencladorPracticaResponseDTO> obtenerNomencladorPorId(@PathVariable Long id) {
        NomencladorPracticaResponseDTO nomenclador = nomencladorPracticaService.obtenerNomencladorPorId(id);
        return ResponseEntity.ok(nomenclador);
    }

    @GetMapping
    @Operation(summary = "Listar todos los nomencladoras", description = "Obtiene la lista de todos los nomencladoras registrados")
    public ResponseEntity<List<NomencladorPracticaResponseDTO>> listarTodos() {
        List<NomencladorPracticaResponseDTO> nomencladoras = nomencladorPracticaService.listarTodos();
        return ResponseEntity.ok(nomencladoras);
    }

    @GetMapping("/obra-social/{idObraSocial}")
    @Operation(summary = "Listar nomencladoras de una obra social", description = "Obtiene todos los nomencladoras de una obra social específica")
    public ResponseEntity<List<NomencladorPracticaResponseDTO>> listarNomencladorDeObraSocial(@PathVariable Long idObraSocial) {
        List<NomencladorPracticaResponseDTO> nomencladoras = nomencladorPracticaService.listarNomencladorDeObraSocial(idObraSocial);
        return ResponseEntity.ok(nomencladoras);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un nomenclador", description = "Actualiza los datos de un nomenclador existente")
    public ResponseEntity<NomencladorPracticaResponseDTO> actualizarNomenclador(
            @PathVariable Long id,
            @Valid @RequestBody NomencladorPracticaRequestDTO dto) {
        NomencladorPracticaResponseDTO nomenclador = nomencladorPracticaService.actualizarNomenclador(id, dto);
        return ResponseEntity.ok(nomenclador);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un nomenclador", description = "Elimina un nomenclador del sistema")
    public ResponseEntity<Void> eliminarNomenclador(@PathVariable Long id) {
        nomencladorPracticaService.eliminarNomenclador(id);
        return ResponseEntity.noContent().build();
    }
}

