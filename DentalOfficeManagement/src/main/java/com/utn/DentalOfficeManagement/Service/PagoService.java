package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.PagoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PagoResponseDTO;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.PagoMapper;
import com.utn.DentalOfficeManagement.Model.Pago;
import com.utn.DentalOfficeManagement.Repository.PagoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PagoService {

    private final PagoRepository pagoRepository;
    private final PagoMapper pagoMapper;

    public PagoService(PagoRepository pagoRepository, PagoMapper pagoMapper) {
        this.pagoRepository = pagoRepository;
        this.pagoMapper = pagoMapper;
    }

    public PagoResponseDTO crearPago(PagoRequestDTO dto) {
        Pago pago = pagoMapper.requestDtoToEntity(dto);
        Pago pagoGuardado = pagoRepository.save(pago);
        return pagoMapper.entityToResponseDto(pagoGuardado);
    }

    @Transactional(readOnly = true)
    public PagoResponseDTO obtenerPagoPorId(Long id) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago", "id", id));
        return pagoMapper.entityToResponseDto(pago);
    }

    @Transactional(readOnly = true)
    public List<PagoResponseDTO> listarTodos() {
        return pagoRepository.findAll().stream()
                .map(pagoMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PagoResponseDTO> listarPagosPorPaciente(Long idPaciente) {
        return pagoRepository.findByPracticaRealizada_Turno_Paciente_IdPaciente(idPaciente).stream()
                .map(pagoMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PagoResponseDTO> listarPagosPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return pagoRepository.findByFechaPagoBetween(fechaInicio, fechaFin).stream()
                .map(pagoMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public PagoResponseDTO actualizarPago(Long id, PagoRequestDTO dto) {
        Pago pago = pagoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pago", "id", id));

        pago.setSaldo(dto.getSaldo());
        pago.setMedio(dto.getMedio());
        Pago pagoActualizado = pagoRepository.save(pago);
        return pagoMapper.entityToResponseDto(pagoActualizado);
    }

    public void eliminarPago(Long id) {
        if (!pagoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pago", "id", id);
        }
        pagoRepository.deleteById(id);
    }
}

