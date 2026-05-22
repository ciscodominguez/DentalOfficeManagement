package com.utn.DentalOfficeManagement.Service;

import com.utn.DentalOfficeManagement.DTO.Request.ObraSocialRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.ObraSocialResponseDTO;
import com.utn.DentalOfficeManagement.Exception.DuplicateResourceException;
import com.utn.DentalOfficeManagement.Exception.ResourceNotFoundException;
import com.utn.DentalOfficeManagement.Mapper.ObraSocialMapper;
import com.utn.DentalOfficeManagement.Model.ObraSocial;
import com.utn.DentalOfficeManagement.Repository.ObraSocialRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ObraSocialService {

    private final ObraSocialRepository obraSocialRepository;
    private final ObraSocialMapper obraSocialMapper;

    public ObraSocialService(ObraSocialRepository obraSocialRepository, ObraSocialMapper obraSocialMapper) {
        this.obraSocialRepository = obraSocialRepository;
        this.obraSocialMapper = obraSocialMapper;
    }

    public ObraSocialResponseDTO crearObraSocial(ObraSocialRequestDTO dto) {
        if (obraSocialRepository.existsByCuit(dto.getCuit())) {
            throw new DuplicateResourceException("Obra Social", "CUIT", dto.getCuit());
        }
        ObraSocial obraSocial = obraSocialMapper.requestDtoToEntity(dto);
        ObraSocial obraSocialGuardada = obraSocialRepository.save(obraSocial);
        return obraSocialMapper.entityToResponseDto(obraSocialGuardada);
    }

    @Transactional(readOnly = true)
    public ObraSocialResponseDTO obtenerObraSocialPorId(Integer id) {
        ObraSocial obraSocial = obraSocialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Obra Social", "id", id));
        return obraSocialMapper.entityToResponseDto(obraSocial);
    }

    @Transactional(readOnly = true)
    public List<ObraSocialResponseDTO> listarTodas() {
        return obraSocialRepository.findAll().stream()
                .map(obraSocialMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ObraSocialResponseDTO> buscarPorNombre(String nombre) {
        return obraSocialRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(obraSocialMapper::entityToResponseDto)
                .collect(Collectors.toList());
    }

    public ObraSocialResponseDTO actualizarObraSocial(Integer id, ObraSocialRequestDTO dto) {
        ObraSocial obraSocial = obraSocialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Obra Social", "id", id));

        if (obraSocialRepository.existsByCuitAndIdObraSocialNot(dto.getCuit(), id)) {
            throw new DuplicateResourceException("Obra Social", "CUIT", dto.getCuit());
        }

        obraSocial.setNombre(dto.getNombre());
        obraSocial.setCuit(dto.getCuit());
        obraSocial.setTelefono(dto.getTelefono());
        ObraSocial obraSocialActualizada = obraSocialRepository.save(obraSocial);
        return obraSocialMapper.entityToResponseDto(obraSocialActualizada);
    }

    public void eliminarObraSocial(Integer id) {
        if (!obraSocialRepository.existsById(id)) {
            throw new ResourceNotFoundException("Obra Social", "id", id);
        }
        obraSocialRepository.deleteById(id);
    }
}

