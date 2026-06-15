package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PracticaRealizadaRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PracticaRealizadaResponseDTO;
import com.utn.DentalOfficeManagement.Model.CoberturaPlan;
import com.utn.DentalOfficeManagement.Model.NomencladorPractica;
import com.utn.DentalOfficeManagement.Model.Pago;
import com.utn.DentalOfficeManagement.Model.PiezaDental;
import com.utn.DentalOfficeManagement.Model.Practica;
import com.utn.DentalOfficeManagement.Model.PracticaRealizada;
import com.utn.DentalOfficeManagement.Model.Turno;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class PracticaRealizadaMapperImpl implements PracticaRealizadaMapper {

    @Override
    public PracticaRealizadaResponseDTO toResponse(PracticaRealizada practicaRealizada) {
        if ( practicaRealizada == null ) {
            return null;
        }

        PracticaRealizadaResponseDTO practicaRealizadaResponseDTO = new PracticaRealizadaResponseDTO();

        practicaRealizadaResponseDTO.setPracticaId( practicaRealizadaPracticaIdPractica( practicaRealizada ) );
        practicaRealizadaResponseDTO.setPracticaNombre( practicaRealizadaPracticaNombre( practicaRealizada ) );
        practicaRealizadaResponseDTO.setPiezaDentalId( practicaRealizadaPiezaDentalIdPiezaDental( practicaRealizada ) );
        Integer numeroPieza = practicaRealizadaPiezaDentalNumeroPieza( practicaRealizada );
        if ( numeroPieza != null ) {
            practicaRealizadaResponseDTO.setNumeroPieza( BigDecimal.valueOf( numeroPieza ) );
        }
        practicaRealizadaResponseDTO.setIdNomencladorPractica( practicaRealizadaNomencladorPracticaIdNomencladorPractica( practicaRealizada ) );
        practicaRealizadaResponseDTO.setCodigoExterno( practicaRealizadaNomencladorPracticaCodigoExterno( practicaRealizada ) );
        practicaRealizadaResponseDTO.setIdCoberturaPlan( practicaRealizadaCoberturaPlanIdCoberturaPlan( practicaRealizada ) );
        practicaRealizadaResponseDTO.setPorcentajeCobertura( practicaRealizadaCoberturaPlanPorcentajeCobertura( practicaRealizada ) );
        practicaRealizadaResponseDTO.setIdPago( practicaRealizadaPagoIdPago( practicaRealizada ) );
        practicaRealizadaResponseDTO.setMedioPago( practicaRealizadaPagoMedio( practicaRealizada ) );
        practicaRealizadaResponseDTO.setIdTurno( practicaRealizadaTurnoIdTurno( practicaRealizada ) );
        practicaRealizadaResponseDTO.setFechaTurno( practicaRealizadaTurnoFecha( practicaRealizada ) );
        practicaRealizadaResponseDTO.setIdPracticaRealizada( practicaRealizada.getIdPracticaRealizada() );
        practicaRealizadaResponseDTO.setPrecioCobrado( practicaRealizada.getPrecioCobrado() );

        return practicaRealizadaResponseDTO;
    }

    @Override
    public PracticaRealizada toEntity(PracticaRealizadaRequestDTO request) {
        if ( request == null ) {
            return null;
        }

        PracticaRealizada practicaRealizada = new PracticaRealizada();

        practicaRealizada.setPrecioCobrado( request.getPrecioCobrado() );

        return practicaRealizada;
    }

    private Long practicaRealizadaPracticaIdPractica(PracticaRealizada practicaRealizada) {
        Practica practica = practicaRealizada.getPractica();
        if ( practica == null ) {
            return null;
        }
        return practica.getIdPractica();
    }

    private String practicaRealizadaPracticaNombre(PracticaRealizada practicaRealizada) {
        Practica practica = practicaRealizada.getPractica();
        if ( practica == null ) {
            return null;
        }
        return practica.getNombre();
    }

    private Long practicaRealizadaPiezaDentalIdPiezaDental(PracticaRealizada practicaRealizada) {
        PiezaDental piezaDental = practicaRealizada.getPiezaDental();
        if ( piezaDental == null ) {
            return null;
        }
        return piezaDental.getIdPiezaDental();
    }

    private Integer practicaRealizadaPiezaDentalNumeroPieza(PracticaRealizada practicaRealizada) {
        PiezaDental piezaDental = practicaRealizada.getPiezaDental();
        if ( piezaDental == null ) {
            return null;
        }
        return piezaDental.getNumeroPieza();
    }

    private Long practicaRealizadaNomencladorPracticaIdNomencladorPractica(PracticaRealizada practicaRealizada) {
        NomencladorPractica nomencladorPractica = practicaRealizada.getNomencladorPractica();
        if ( nomencladorPractica == null ) {
            return null;
        }
        return nomencladorPractica.getIdNomencladorPractica();
    }

    private String practicaRealizadaNomencladorPracticaCodigoExterno(PracticaRealizada practicaRealizada) {
        NomencladorPractica nomencladorPractica = practicaRealizada.getNomencladorPractica();
        if ( nomencladorPractica == null ) {
            return null;
        }
        return nomencladorPractica.getCodigoExterno();
    }

    private Long practicaRealizadaCoberturaPlanIdCoberturaPlan(PracticaRealizada practicaRealizada) {
        CoberturaPlan coberturaPlan = practicaRealizada.getCoberturaPlan();
        if ( coberturaPlan == null ) {
            return null;
        }
        return coberturaPlan.getIdCoberturaPlan();
    }

    private BigDecimal practicaRealizadaCoberturaPlanPorcentajeCobertura(PracticaRealizada practicaRealizada) {
        CoberturaPlan coberturaPlan = practicaRealizada.getCoberturaPlan();
        if ( coberturaPlan == null ) {
            return null;
        }
        return coberturaPlan.getPorcentajeCobertura();
    }

    private Long practicaRealizadaPagoIdPago(PracticaRealizada practicaRealizada) {
        Pago pago = practicaRealizada.getPago();
        if ( pago == null ) {
            return null;
        }
        return pago.getIdPago();
    }

    private String practicaRealizadaPagoMedio(PracticaRealizada practicaRealizada) {
        Pago pago = practicaRealizada.getPago();
        if ( pago == null ) {
            return null;
        }
        return pago.getMedio();
    }

    private Long practicaRealizadaTurnoIdTurno(PracticaRealizada practicaRealizada) {
        Turno turno = practicaRealizada.getTurno();
        if ( turno == null ) {
            return null;
        }
        return turno.getIdTurno();
    }

    private LocalDate practicaRealizadaTurnoFecha(PracticaRealizada practicaRealizada) {
        Turno turno = practicaRealizada.getTurno();
        if ( turno == null ) {
            return null;
        }
        return turno.getFecha();
    }
}
