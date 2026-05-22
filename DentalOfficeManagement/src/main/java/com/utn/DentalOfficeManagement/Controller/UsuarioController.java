package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.UsuarioRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.UsuarioResponseDTO;
import com.utn.DentalOfficeManagement.Service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuarios", description = "Gestión de usuarios del sistema")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo usuario", description = "Crea un nuevo usuario en el sistema con rol asignado")
    public ResponseEntity<UsuarioResponseDTO> crearUsuario(@Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuario = usuarioService.crearUsuario(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", description = "Obtiene un usuario específico por su ID")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable Integer id) {
        UsuarioResponseDTO usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Obtener usuario por username", description = "Obtiene un usuario específico por su username")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorUsername(@PathVariable String username) {
        UsuarioResponseDTO usuario = usuarioService.obtenerUsuarioPorUsername(username);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping
    @Operation(summary = "Listar todos los usuarios", description = "Obtiene la lista de todos los usuarios del sistema")
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un usuario", description = "Actualiza los datos de un usuario existente")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioRequestDTO dto) {
        UsuarioResponseDTO usuario = usuarioService.actualizarUsuario(id, dto);
        return ResponseEntity.ok(usuario);
    }

    @PatchMapping("/{id}/cambiar-contrasenia")
    @Operation(summary = "Cambiar contraseña de usuario", description = "Cambia la contraseña de un usuario específico")
    public ResponseEntity<UsuarioResponseDTO> cambiarContrasenia(
            @PathVariable Integer id,
            @RequestParam String nuevaContrasenia) {
        UsuarioResponseDTO usuario = usuarioService.cambiarContrasenia(id, nuevaContrasenia);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un usuario", description = "Elimina un usuario del sistema")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

