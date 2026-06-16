package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.NomencladorPracticaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.NomencladorPracticaResponseDTO;
import com.utn.DentalOfficeManagement.Exception.DuplicateResourceException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.NomencladorPracticaMapper;
import com.utn.DentalOfficeManagement.Model.NomencladorPractica;
import com.utn.DentalOfficeManagement.Model.ObraSocial;
import com.utn.DentalOfficeManagement.Model.Practica;
import com.utn.DentalOfficeManagement.Repository.NomencladorPracticaRepository;
import com.utn.DentalOfficeManagement.Repository.ObraSocialRepository;
import com.utn.DentalOfficeManagement.Repository.PracticaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NomencladorPracticaService {

    private final NomencladorPracticaRepository nomencladorPracticaRepository;
    private final ObraSocialRepository obraSocialRepository;
    private final PracticaRepository practicaRepository;
    private final NomencladorPracticaMapper nomencladorPracticaMapper;

    public NomencladorPracticaService(NomencladorPracticaRepository nomencladorPracticaRepository, ObraSocialRepository obraSocialRepository, PracticaRepository practicaRepository, NomencladorPracticaMapper nomencladorPracticaMapper) {
        this.nomencladorPracticaRepository = nomencladorPracticaRepository;
        this.obraSocialRepository = obraSocialRepository;
        this.practicaRepository = practicaRepository;
        this.nomencladorPracticaMapper = nomencladorPracticaMapper;
    }

    public NomencladorPracticaResponseDTO crearNomenclador(NomencladorPracticaRequestDTO dto) {
        ObraSocial obraSocial = obraSocialRepository.findById(dto.getObraSocialId())
                .orElseThrow(() -> new ResourceNotFoundException("Obra Social", "id", dto.getObraSocialId()));

        Practica practica = practicaRepository.findById(dto.getPracticaId())
                .orElseThrow(() -> new ResourceNotFoundException("Práctica", "id", dto.getPracticaId()));

        if (nomencladorPracticaRepository.existsByObraSocial_IdObraSocialAndPractica_IdPractica(obraSocial.getIdObraSocial(), practica.getIdPractica())) {
            throw new DuplicateResourceException("Nomenclador", "combinación de obra social y práctica", dto.getObraSocialId() + "-" + dto.getPracticaId());
        }

        NomencladorPractica nomenclador = nomencladorPracticaMapper.requestDtoToEntity(dto);
        nomenclador.setObraSocial(obraSocial);
        nomenclador.setPractica(practica);
        NomencladorPractica nomencladorGuardado = nomencladorPracticaRepository.save(nomenclador);
        return nomencladorPracticaMapper.entityToResponseDto(nomencladorGuardado);
    }

    @Transactional(readOnly = true)
    public NomencladorPracticaResponseDTO obtenerNomencladorPorId(Long id) {
        NomencladorPractica nomenclador = nomencladorPracticaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nomenclador", "id", id));
        return nomencladorPracticaMapper.entityToResponseDto(nomenclador);
    }

    @Transactional(readOnly = true)
    public List<NomencladorPracticaResponseDTO> listarTodos() {
        return nomencladorPracticaRepository.findAll().stream()
                .map(nomencladorPracticaMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NomencladorPracticaResponseDTO> listarNomencladorDeObraSocial(Long idObraSocial) {
        return nomencladorPracticaRepository.findByObraSocial_IdObraSocial(idObraSocial).stream()
                .map(nomencladorPracticaMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public NomencladorPracticaResponseDTO actualizarNomenclador(Long id, NomencladorPracticaRequestDTO dto) {
        NomencladorPractica nomenclador = nomencladorPracticaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nomenclador", "id", id));

        ObraSocial obraSocial = obraSocialRepository.findById(dto.getObraSocialId())
                .orElseThrow(() -> new ResourceNotFoundException("Obra Social", "id", dto.getObraSocialId()));

        Practica practica = practicaRepository.findById(dto.getPracticaId())
                .orElseThrow(() -> new ResourceNotFoundException("Práctica", "id", dto.getPracticaId()));

        if (nomencladorPracticaRepository.existsByObraSocial_IdObraSocialAndPractica_IdPracticaAndIdNomencladorPracticaNot(obraSocial.getIdObraSocial(), practica.getIdPractica(), id)) {
            throw new DuplicateResourceException("Nomenclador", "combinación de obra social y práctica", dto.getObraSocialId() + "-" + dto.getPracticaId());
        }

        nomenclador.setObraSocial(obraSocial);
        nomenclador.setPractica(practica);
        nomenclador.setCodigoExterno(dto.getCodigoExterno());
        NomencladorPractica nomencladorActualizado = nomencladorPracticaRepository.save(nomenclador);
        return nomencladorPracticaMapper.entityToResponseDto(nomencladorActualizado);
    }

    public void eliminarNomenclador(Long id) {
        if (!nomencladorPracticaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Nomenclador", "id", id);
        }
        nomencladorPracticaRepository.deleteById(id);
    }
}

