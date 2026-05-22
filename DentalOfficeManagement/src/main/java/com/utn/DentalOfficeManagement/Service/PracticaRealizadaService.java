package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.PracticaRealizadaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PracticaRealizadaResponseDTO;
import com.utn.DentalOfficeManagement.Exception.InvalidOperationException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.PracticaRealizadaMapper;
import com.utn.DentalOfficeManagement.Model.*;
import com.utn.DentalOfficeManagement.Repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PracticaRealizadaService {

    private final PracticaRealizadaRepository practicaRealizadaRepository;
    private final PracticaRepository practicaRepository;
    private final PiezaDentalRepository piezaDentalRepository;
    private final NomencladorPracticaRepository nomencladorPracticaRepository;
    private final CoberturaPlanRepository coberturaPlanRepository;
    private final PagoRepository pagoRepository;
    private final TurnoRepository turnoRepository;
    private final PracticaRealizadaMapper practicaRealizadaMapper;

    public PracticaRealizadaService(PracticaRealizadaRepository practicaRealizadaRepository, PracticaRepository practicaRepository, PiezaDentalRepository piezaDentalRepository, NomencladorPracticaRepository nomencladorPracticaRepository, CoberturaPlanRepository coberturaPlanRepository, PagoRepository pagoRepository, TurnoRepository turnoRepository, PracticaRealizadaMapper practicaRealizadaMapper) {
        this.practicaRealizadaRepository = practicaRealizadaRepository;
        this.practicaRepository = practicaRepository;
        this.piezaDentalRepository = piezaDentalRepository;
        this.nomencladorPracticaRepository = nomencladorPracticaRepository;
        this.coberturaPlanRepository = coberturaPlanRepository;
        this.pagoRepository = pagoRepository;
        this.turnoRepository = turnoRepository;
        this.practicaRealizadaMapper = practicaRealizadaMapper;
    }

    public PracticaRealizadaResponseDTO crearPracticaRealizada(PracticaRealizadaRequestDTO dto) {
        Practica practica = practicaRepository.findById(dto.getPracticaId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Práctica", "id", dto.getPracticaId()));

        PiezaDental piezaDental = piezaDentalRepository.findById(dto.getPiezaDentalId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Pieza Dental", "id", dto.getPiezaDentalId()));

        NomencladorPractica nomenclador = nomencladorPracticaRepository.findById(dto.getIdNomencladorPractica().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Nomenclador", "id", dto.getIdNomencladorPractica()));

        CoberturaPlan cobertura = coberturaPlanRepository.findById(dto.getIdCoberturaPlan().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Cobertura", "id", dto.getIdCoberturaPlan()));

        Turno turno = turnoRepository.findById(dto.getIdTurno().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", dto.getIdTurno()));

        Pago pago = null;
        if (dto.getIdPago() != null) {
            pago = pagoRepository.findById(dto.getIdPago().intValue())
                    .orElseThrow(() -> new ResourceNotFoundException("Pago", "id", dto.getIdPago()));
        }

        PracticaRealizada practicaRealizada = practicaRealizadaMapper.toEntity(dto);
        practicaRealizada.setPractica(practica);
        practicaRealizada.setPiezaDental(piezaDental);
        practicaRealizada.setNomencladorPractica(nomenclador);
        practicaRealizada.setCoberturaPlan(cobertura);
        practicaRealizada.setTurno(turno);
        if (pago != null) {
            practicaRealizada.setPago(pago);
        }
        PracticaRealizada practicaRealizadaGuardada = practicaRealizadaRepository.save(practicaRealizada);
        return practicaRealizadaMapper.toResponse(practicaRealizadaGuardada);
    }

    @Transactional(readOnly = true)
    public PracticaRealizadaResponseDTO obtenerPracticaRealizadaPorId(Long id) {
        PracticaRealizada practicaRealizada = practicaRealizadaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Práctica Realizada", "id", id));
        return practicaRealizadaMapper.toResponse(practicaRealizada);
    }

    @Transactional(readOnly = true)
    public List<PracticaRealizadaResponseDTO> listarTodas() {
        return practicaRealizadaRepository.findAll().stream()
                .map(practicaRealizadaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PracticaRealizadaResponseDTO> listarPracticasRealizadasPorTurno(Integer idTurno) {
        return practicaRealizadaRepository.findByTurno_IdTurno(idTurno).stream()
                .map(practicaRealizadaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PracticaRealizadaResponseDTO> listarPracticasRealizadasPorPaciente(Integer idPaciente) {
        return practicaRealizadaRepository.findByTurno_Paciente_IdPaciente(idPaciente).stream()
                .map(practicaRealizadaMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PracticaRealizadaResponseDTO> listarPracticasRealizadasSinPago() {
        return practicaRealizadaRepository.findByPagoIsNull().stream()
                .map(practicaRealizadaMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PracticaRealizadaResponseDTO actualizarPracticaRealizada(Long id, PracticaRealizadaRequestDTO dto) {
        PracticaRealizada practicaRealizada = practicaRealizadaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Práctica Realizada", "id", id));

        Practica practica = practicaRepository.findById(dto.getPracticaId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Práctica", "id", dto.getPracticaId()));

        PiezaDental piezaDental = piezaDentalRepository.findById(dto.getPiezaDentalId().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Pieza Dental", "id", dto.getPiezaDentalId()));

        NomencladorPractica nomenclador = nomencladorPracticaRepository.findById(dto.getIdNomencladorPractica().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Nomenclador", "id", dto.getIdNomencladorPractica()));

        CoberturaPlan cobertura = coberturaPlanRepository.findById(dto.getIdCoberturaPlan().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Cobertura", "id", dto.getIdCoberturaPlan()));

        Turno turno = turnoRepository.findById(dto.getIdTurno().intValue())
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", dto.getIdTurno()));

        Pago pago = null;
        if (dto.getIdPago() != null) {
            pago = pagoRepository.findById(dto.getIdPago().intValue())
                    .orElseThrow(() -> new ResourceNotFoundException("Pago", "id", dto.getIdPago()));
        }

        practicaRealizada.setPractica(practica);
        practicaRealizada.setPiezaDental(piezaDental);
        practicaRealizada.setNomencladorPractica(nomenclador);
        practicaRealizada.setCoberturaPlan(cobertura);
        practicaRealizada.setTurno(turno);
        practicaRealizada.setPrecioCobrado(dto.getPrecioCobrado());
        if (pago != null) {
            practicaRealizada.setPago(pago);
        }
        PracticaRealizada practicaRealizadaActualizada = practicaRealizadaRepository.save(practicaRealizada);
        return practicaRealizadaMapper.toResponse(practicaRealizadaActualizada);
    }

    public PracticaRealizadaResponseDTO asignarPago(Long id, Integer idPago) {
        PracticaRealizada practicaRealizada = practicaRealizadaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Práctica Realizada", "id", id));

        Pago pago = pagoRepository.findById(idPago)
                .orElseThrow(() -> new ResourceNotFoundException("Pago", "id", idPago));

        practicaRealizada.setPago(pago);
        PracticaRealizada practicaRealizadaActualizada = practicaRealizadaRepository.save(practicaRealizada);
        return practicaRealizadaMapper.toResponse(practicaRealizadaActualizada);
    }

    public void eliminarPracticaRealizada(Long id) {
        if (!practicaRealizadaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Práctica Realizada", "id", id);
        }
        practicaRealizadaRepository.deleteById(id);
    }
}

