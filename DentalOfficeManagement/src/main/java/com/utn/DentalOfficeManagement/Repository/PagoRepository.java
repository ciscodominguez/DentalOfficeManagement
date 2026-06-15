package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {
    List<Pago> findByMedio(String medio);

    @Query("""
        SELECT DISTINCT p FROM Pago p, PracticaRealizada pr
        WHERE pr.pago = p
          AND pr.turno.paciente.idPaciente = :idPaciente
    """)
    List<Pago> findByPacienteId(@Param("idPaciente") Long idPaciente);

    @Query("""
        SELECT DISTINCT p FROM Pago p, PracticaRealizada pr
        WHERE pr.pago = p
          AND pr.turno.fecha BETWEEN :desde AND :hasta
    """)
    List<Pago> findByRangoFecha(
            @Param("desde") LocalDate desde,
            @Param("hasta") LocalDate hasta
    );


    List<Pago> findByFechaPagoBetween(LocalDate fechaInicio, LocalDate fechaFin);
}
