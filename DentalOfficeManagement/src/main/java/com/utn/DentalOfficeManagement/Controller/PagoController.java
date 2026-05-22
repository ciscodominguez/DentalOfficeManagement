package com.utn.DentalOfficeManagement.Controller;

import com.utn.DentalOfficeManagement.DTO.Request.PagoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PagoResponseDTO;
import com.utn.DentalOfficeManagement.Service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Gestión de pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo pago", description = "Crea un nuevo pago en el sistema")
    public ResponseEntity<PagoResponseDTO> crearPago(@Valid @RequestBody PagoRequestDTO dto) {
        PagoResponseDTO pago = pagoService.crearPago(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pago);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pago por ID", description = "Obtiene un pago específico por su ID")
    public ResponseEntity<PagoResponseDTO> obtenerPagoPorId(@PathVariable Integer id) {
        PagoResponseDTO pago = pagoService.obtenerPagoPorId(id);
        return ResponseEntity.ok(pago);
    }

    @GetMapping
    @Operation(summary = "Listar todos los pagos", description = "Obtiene la lista de todos los pagos registrados")
    public ResponseEntity<List<PagoResponseDTO>> listarTodos() {
        List<PagoResponseDTO> pagos = pagoService.listarTodos();
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/paciente/{idPaciente}")
    @Operation(summary = "Listar pagos por paciente", description = "Obtiene todos los pagos de un paciente específico")
    public ResponseEntity<List<PagoResponseDTO>> listarPagosPorPaciente(@PathVariable Integer idPaciente) {
        List<PagoResponseDTO> pagos = pagoService.listarPagosPorPaciente(idPaciente);
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/filtrar")
    @Operation(summary = "Filtrar pagos por rango de fecha", description = "Obtiene pagos dentro de un rango de fechas especificado")
    public ResponseEntity<List<PagoResponseDTO>> listarPagosPorRangoFecha(
            @RequestParam LocalDate fechaInicio,
            @RequestParam LocalDate fechaFin) {
        List<PagoResponseDTO> pagos = pagoService.listarPagosPorRangoFecha(fechaInicio, fechaFin);
        return ResponseEntity.ok(pagos);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un pago", description = "Actualiza los datos de un pago existente")
    public ResponseEntity<PagoResponseDTO> actualizarPago(
            @PathVariable Integer id,
            @Valid @RequestBody PagoRequestDTO dto) {
        PagoResponseDTO pago = pagoService.actualizarPago(id, dto);
        return ResponseEntity.ok(pago);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pago", description = "Elimina un pago del sistema")
    public ResponseEntity<Void> eliminarPago(@PathVariable Integer id) {
        pagoService.eliminarPago(id);
        return ResponseEntity.noContent().build();
    }
}

