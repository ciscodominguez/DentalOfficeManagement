package com.utn.DentalOfficeManagement.Mapper;

import com.utn.DentalOfficeManagement.DTO.Request.TurnoRequestDTO;
import com.utn.DentalOfficeManagement.DTO.Response.TurnoResponseDTO;
import com.utn.DentalOfficeManagement.Model.Odontologo;
import com.utn.DentalOfficeManagement.Model.Paciente;
import com.utn.DentalOfficeManagement.Model.Turno;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-15T01:04:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.11 (Microsoft)"
)
@Component
public class TurnoMapperImpl implements TurnoMapper {

    @Override
    public TurnoResponseDTO toResponse(Turno turno) {
        if ( turno == null ) {
            return null;
        }

        TurnoResponseDTO turnoResponseDTO = new TurnoResponseDTO();

        turnoResponseDTO.setPacienteId( turnoPacienteIdPaciente( turno ) );
        turnoResponseDTO.setPacienteNombre( turnoPacienteNombre( turno ) );
        turnoResponseDTO.setPacienteDni( turnoPacienteDni( turno ) );
        turnoResponseDTO.setOdontologoId( turnoOdontologoIdOdontologo( turno ) );
        turnoResponseDTO.setOdontologoNombre( turnoOdontologoNombre( turno ) );
        turnoResponseDTO.setOdontologoMatricula( turnoOdontologoMatricula( turno ) );
        turnoResponseDTO.setIdTurno( turno.getIdTurno() );
        turnoResponseDTO.setFecha( turno.getFecha() );
        turnoResponseDTO.setHora( turno.getHora() );
        turnoResponseDTO.setEstado( turno.getEstado() );
        turnoResponseDTO.setMotivo( turno.getMotivo() );

        return turnoResponseDTO;
    }

    @Override
    public Turno toEntity(TurnoRequestDTO request) {
        if ( request == null ) {
            return null;
        }

        Turno turno = new Turno();

        turno.setFecha( request.getFecha() );
        turno.setHora( request.getHora() );
        turno.setEstado( request.getEstado() );
        turno.setMotivo( request.getMotivo() );

        return turno;
    }

    private Long turnoPacienteIdPaciente(Turno turno) {
        Paciente paciente = turno.getPaciente();
        if ( paciente == null ) {
            return null;
        }
        return paciente.getIdPaciente();
    }

    private String turnoPacienteNombre(Turno turno) {
        Paciente paciente = turno.getPaciente();
        if ( paciente == null ) {
            return null;
        }
        return paciente.getNombre();
    }

    private String turnoPacienteDni(Turno turno) {
        Paciente paciente = turno.getPaciente();
        if ( paciente == null ) {
            return null;
        }
        return paciente.getDni();
    }

    private Long turnoOdontologoIdOdontologo(Turno turno) {
        Odontologo odontologo = turno.getOdontologo();
        if ( odontologo == null ) {
            return null;
        }
        return odontologo.getIdOdontologo();
    }

    private String turnoOdontologoNombre(Turno turno) {
        Odontologo odontologo = turno.getOdontologo();
        if ( odontologo == null ) {
            return null;
        }
        return odontologo.getNombre();
    }

    private String turnoOdontologoMatricula(Turno turno) {
        Odontologo odontologo = turno.getOdontologo();
        if ( odontologo == null ) {
            return null;
        }
        return odontologo.getMatricula();
    }
}
