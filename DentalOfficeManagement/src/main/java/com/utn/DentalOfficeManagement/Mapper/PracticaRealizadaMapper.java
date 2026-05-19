package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PracticaRealizadaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PracticaRealizadaResponseDTO;
import com.utn.DentalOfficeManagement.Model.PracticaRealizada;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PracticaRealizadaMapper {
    @Mapping(source = "practica.idPractica", target = "practicaId")
    @Mapping(source = "practica.nombre", target = "practicaNombre")
    @Mapping(source = "piezaDental.idPiezaDental", target = "piezaDentalId")
    @Mapping(source = "piezaDental.numeroPieza", target = "numeroPieza")
    @Mapping(source = "nomencladorPractica.idNomencladorPractica", target = "idNomencladorPractica")
    @Mapping(source = "nomencladorPractica.codigoExterno", target = "codigoExterno")
    @Mapping(source = "coberturaPlan.idCoberturaPlan", target = "idCoberturaPlan")
    @Mapping(source = "coberturaPlan.porcentajeCobertura", target = "porcentajeCobertura")
    @Mapping(source = "pago.idPago", target = "idPago")
    @Mapping(source = "pago.medio", target = "medioPago")
    @Mapping(source = "turno.idTurno", target = "idTurno")
    @Mapping(source = "turno.fecha", target = "fechaTurno")
    PracticaRealizadaResponseDTO toResponse(PracticaRealizada practicaRealizada);

    @Mapping(target = "idPracticaRealizada", ignore = true)
    @Mapping(target = "practica", ignore = true)
    @Mapping(target = "piezaDental", ignore = true)
    @Mapping(target = "nomencladorPractica", ignore = true)
    @Mapping(target = "coberturaPlan", ignore = true)
    @Mapping(target = "pago", ignore = true)
    @Mapping(target = "turno", ignore = true)
    PracticaRealizada toEntity(PracticaRealizadaRequestDTO request);
}
