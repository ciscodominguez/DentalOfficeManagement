package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.RolRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.RolResponseDTO;
import com.utn.DentalOfficeManagement.Service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "Roles", description = "Gestión de roles del sistema")
public class RolController {

    private final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo rol", description = "Crea un nuevo rol en el sistema")
    public ResponseEntity<RolResponseDTO> crearRol(@Valid @RequestBody RolRequestDTO dto) {
        RolResponseDTO rol = rolService.crearRol(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(rol);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener rol por ID", description = "Obtiene un rol específico por su ID")
    public ResponseEntity<RolResponseDTO> obtenerRolPorId(@PathVariable Integer id) {
        RolResponseDTO rol = rolService.obtenerRolPorId(id);
        return ResponseEntity.ok(rol);
    }

    @GetMapping
    @Operation(summary = "Listar todos los roles", description = "Obtiene la lista de todos los roles registrados")
    public ResponseEntity<List<RolResponseDTO>> listarTodosLosRoles() {
        List<RolResponseDTO> roles = rolService.listarTodosLosRoles();
        return ResponseEntity.ok(roles);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un rol", description = "Actualiza los datos de un rol existente")
    public ResponseEntity<RolResponseDTO> actualizarRol(
            @PathVariable Integer id,
            @Valid @RequestBody RolRequestDTO dto) {
        RolResponseDTO rol = rolService.actualizarRol(id, dto);
        return ResponseEntity.ok(rol);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un rol", description = "Elimina un rol del sistema")
    public ResponseEntity<Void> eliminarRol(@PathVariable Integer id) {
        rolService.eliminarRol(id);
        return ResponseEntity.noContent().build();
    }
}

