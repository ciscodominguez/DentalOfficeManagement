package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.TurnoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.TurnoResponseDTO;
import com.utn.DentalOfficeManagement.Exception.InvalidOperationException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.TurnoMapper;
import com.utn.DentalOfficeManagement.Model.Odontologo;
import com.utn.DentalOfficeManagement.Model.Paciente;
import com.utn.DentalOfficeManagement.Model.Turno;
import com.utn.DentalOfficeManagement.Repository.OdontologoRepository;
import com.utn.DentalOfficeManagement.Repository.PacienteRepository;
import com.utn.DentalOfficeManagement.Repository.TurnoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TurnoService {

    private final TurnoRepository turnoRepository;
    private final PacienteRepository pacienteRepository;
    private final OdontologoRepository odontologoRepository;
    private final TurnoMapper turnoMapper;

    public TurnoService(TurnoRepository turnoRepository, PacienteRepository pacienteRepository, OdontologoRepository odontologoRepository, TurnoMapper turnoMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.turnoMapper = turnoMapper;
    }

    public TurnoResponseDTO crearTurno(TurnoRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", dto.getPacienteId()));

        Odontologo odontologo = odontologoRepository.findById(dto.getOdontologoId())
                .orElseThrow(() -> new ResourceNotFoundException("Odontólogo", "id", dto.getOdontologoId()));

        // Validar que no exista otro turno para el mismo odontólogo a la misma fecha y hora
        if (turnoRepository.existsByOdontologo_IdOdontologoAndFechaAndHora(odontologo.getIdOdontologo(), dto.getFecha(), dto.getHora())) {
            throw new InvalidOperationException("Ya existe un turno registrado para este odontólogo en la fecha y hora especificada");
        }

        Turno turno = turnoMapper.toEntity(dto);
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        Turno turnoGuardado = turnoRepository.save(turno);
        return turnoMapper.toResponse(turnoGuardado);
    }

    @Transactional(readOnly = true)
    public TurnoResponseDTO obtenerTurnoPorId(Long id) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", id));
        return turnoMapper.toResponse(turno);
    }

    @Transactional(readOnly = true)
    public List<TurnoResponseDTO> listarTodos() {
        return turnoRepository.findAll().stream()
                .map(turnoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TurnoResponseDTO> listarTurnosPorPaciente(Long idPaciente) {
        return turnoRepository.findByPaciente_IdPaciente(idPaciente).stream()
                .map(turnoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TurnoResponseDTO> listarTurnosPorOdontologo(Long idOdontologo) {
        return turnoRepository.findByOdontologo_IdOdontologo(idOdontologo).stream()
                .map(turnoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TurnoResponseDTO> listarTurnosPorEstado(String estado) {
        return turnoRepository.findByEstado(estado).stream()
                .map(turnoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TurnoResponseDTO> listarTurnosPorFecha(LocalDate fecha) {
        return turnoRepository.findByFecha(fecha).stream()
                .map(turnoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TurnoResponseDTO actualizarTurno(Long id, TurnoRequestDTO dto) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", id));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", dto.getPacienteId()));

        Odontologo odontologo = odontologoRepository.findById(dto.getOdontologoId())
                .orElseThrow(() -> new ResourceNotFoundException("Odontólogo", "id", dto.getOdontologoId()));

        // Validar que no exista otro turno para el mismo odontólogo a la misma fecha y hora (excepto el actual)
        if (turnoRepository.existsByOdontologo_IdOdontologoAndFechaAndHoraAndIdTurnoNot(odontologo.getIdOdontologo(), dto.getFecha(), dto.getHora(), id)) {
            throw new InvalidOperationException("Ya existe otro turno para este odontólogo en la fecha y hora especificada");
        }

        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        turno.setFecha(dto.getFecha());
        turno.setHora(dto.getHora());
        turno.setEstado(dto.getEstado());
        turno.setMotivo(dto.getMotivo());
        Turno turnoActualizado = turnoRepository.save(turno);
        return turnoMapper.toResponse(turnoActualizado);
    }

    public TurnoResponseDTO cambiarEstadoTurno(Long id, String nuevoEstado) {
        Turno turno = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno", "id", id));
        turno.setEstado(nuevoEstado);
        Turno turnoActualizado = turnoRepository.save(turno);
        return turnoMapper.toResponse(turnoActualizado);
    }

    public void eliminarTurno(Long id) {
        if (!turnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Turno", "id", id);
        }
        turnoRepository.deleteById(id);
    }
}

