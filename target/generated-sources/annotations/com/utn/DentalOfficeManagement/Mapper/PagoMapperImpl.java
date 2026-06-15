package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.PagoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.PagoResponseDTO;
import com.utn.DentalOfficeManagement.Model.Pago;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class PagoMapperImpl implements PagoMapper {

    @Override
    public Pago requestDtoToEntity(PagoRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Pago pago = new Pago();

        pago.setSaldo( dto.getSaldo() );
        pago.setMedio( dto.getMedio() );

        return pago;
    }

    @Override
    public PagoResponseDTO entityToResponseDto(Pago entity) {
        if ( entity == null ) {
            return null;
        }

        PagoResponseDTO pagoResponseDTO = new PagoResponseDTO();

        pagoResponseDTO.setIdPago( entity.getIdPago() );
        pagoResponseDTO.setSaldo( entity.getSaldo() );
        pagoResponseDTO.setMedio( entity.getMedio() );
        pagoResponseDTO.setFechaPago( entity.getFechaPago() );

        return pagoResponseDTO;
    }

    @Override
    public void updateEntityFromRequestDto(PagoRequestDTO dto, Pago entity) {
        if ( dto == null ) {
            return;
        }

        entity.setSaldo( dto.getSaldo() );
        entity.setMedio( dto.getMedio() );
    }
}
