package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.FichaMedicaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.FichaMedicaResponseDTO;
import com.utn.DentalOfficeManagement.Exception.DuplicateResourceException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.FichaMedicaMapper;
import com.utn.DentalOfficeManagement.Model.FichaMedica;
import com.utn.DentalOfficeManagement.Model.Paciente;
import com.utn.DentalOfficeManagement.Repository.FichaMedicaRepository;
import com.utn.DentalOfficeManagement.Repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FichaMedicaService {

    private final FichaMedicaRepository fichaMedicaRepository;
    private final PacienteRepository pacienteRepository;
    private final FichaMedicaMapper fichaMedicaMapper;

    public FichaMedicaService(FichaMedicaRepository fichaMedicaRepository, PacienteRepository pacienteRepository, FichaMedicaMapper fichaMedicaMapper) {
        this.fichaMedicaRepository = fichaMedicaRepository;
        this.pacienteRepository = pacienteRepository;
        this.fichaMedicaMapper = fichaMedicaMapper;
    }

    public FichaMedicaResponseDTO crearFichaMedica(FichaMedicaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente", "id", dto.getPacienteId()));

        if (fichaMedicaRepository.existsByPaciente_IdPaciente(paciente.getIdPaciente())) {
            throw new DuplicateResourceException("Ficha Médica", "paciente", dto.getPacienteId());
        }

        FichaMedica fichaMedica = fichaMedicaMapper.requestDtoToEntity(dto);
        fichaMedica.setPaciente(paciente);
        FichaMedica fichaMedicaGuardada = fichaMedicaRepository.save(fichaMedica);
        return fichaMedicaMapper.entityToResponseDto(fichaMedicaGuardada);
    }

    @Transactional(readOnly = true)
    public FichaMedicaResponseDTO obtenerFichaMedicaPorPaciente(Long idPaciente) {
        FichaMedica fichaMedica = fichaMedicaRepository.findByPaciente_IdPaciente(idPaciente)
                .orElseThrow(() -> new ResourceNotFoundException("Ficha Médica", "paciente", idPaciente));
        return fichaMedicaMapper.entityToResponseDto(fichaMedica);
    }

    public FichaMedicaResponseDTO actualizarFichaMedica(Long idPaciente, FichaMedicaRequestDTO dto) {
        FichaMedica fichaMedica = fichaMedicaRepository.findByPaciente_IdPaciente(idPaciente)
                .orElseThrow(() -> new ResourceNotFoundException("Ficha Médica", "paciente", idPaciente));

        fichaMedica.setAlergias(dto.getAlergias());
        fichaMedica.setMedicacion(dto.getMedicacion());
        fichaMedica.setAntecedentes(dto.getAntecedentes());
        fichaMedica.setGrupoSanguineo(dto.getGrupoSanguineo());
        FichaMedica fichaMedicaActualizada = fichaMedicaRepository.save(fichaMedica);
        return fichaMedicaMapper.entityToResponseDto(fichaMedicaActualizada);
    }

    public void eliminarFichaMedica(Long idPaciente) {
        FichaMedica fichaMedica = fichaMedicaRepository.findByPaciente_IdPaciente(idPaciente)
                .orElseThrow(() -> new ResourceNotFoundException("Ficha Médica", "paciente", idPaciente));
        fichaMedicaRepository.delete(fichaMedica);
    }
}

