package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.PracticaRealizada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PracticaRealizadaRepository extends JpaRepository<PracticaRealizada, Long> {
    List<PracticaRealizada> findByTurno_IdTurno(Integer idTurno);
    List<PracticaRealizada> findByPago_IdPago(Integer idPago);

    @Query("""
        SELECT pr FROM PracticaRealizada pr
        JOIN pr.turno t
        WHERE t.paciente.idPaciente = :idPaciente
    """)
    List<PracticaRealizada> findByPacienteId(@Param("idPaciente") Integer idPaciente);

    @Query("""
        SELECT pr FROM PracticaRealizada pr
        JOIN pr.turno t
        WHERE t.odontologo.idOdontologo = :idOdontologo
    """)
    List<PracticaRealizada> findByOdontologoId(@Param("idOdontologo") Integer idOdontologo);

    @Query("""
        SELECT pr FROM PracticaRealizada pr
        WHERE pr.pago IS NULL
    """)
    List<PracticaRealizada> findSinPago();

    @Query("""
        SELECT pr FROM PracticaRealizada pr
        JOIN pr.turno t
        WHERE t.paciente.idPaciente = :idPaciente
        AND pr.pago IS NULL
    """)
    List<PracticaRealizada> findSinPagoByPacienteId(@Param("idPaciente") Integer idPaciente);

    List<PracticaRealizada> findByTurno_Paciente_IdPaciente(Integer idPaciente);

    List<PracticaRealizada> findByPagoIsNull();
}

