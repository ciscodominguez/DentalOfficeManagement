package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByMedio(String medio);

    @Query("""
        SELECT p FROM Pago p
        JOIN PracticaRealizada pr ON pr.pago.idPago = p.idPago
        JOIN Turno t ON pr.turno.idTurno = t.idTurno
        WHERE t.paciente.idPaciente = :idPaciente
    """)
    List<Pago> findByPacienteId(@Param("idPaciente") Long idPaciente);

    @Query("""
        SELECT p FROM Pago p
        JOIN PracticaRealizada pr ON pr.pago.idPago = p.idPago
        JOIN Turno t ON pr.turno.idTurno = t.idTurno
        WHERE t.fecha BETWEEN :desde AND :hasta
    """)
    List<Pago> findByRangoFecha(
            @Param("desde") LocalDate desde,
            @Param("hasta") LocalDate hasta
    );

    List<Pago> findByPracticaRealizada_Turno_Paciente_IdPaciente(Long idPaciente);

    List<Pago> findByFechaPagoBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
