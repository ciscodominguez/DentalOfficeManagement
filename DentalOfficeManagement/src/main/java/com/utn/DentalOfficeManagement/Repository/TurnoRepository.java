package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Turno;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByPaciente_IdPaciente(Long idPaciente);
    List<Turno> findByOdontologo_IdOdontologo(Long idOdontologo);
    List<Turno> findByEstado(String estado);
    List<Turno> findByFechaBetween(LocalDate desde, LocalDate hasta);
    List<Turno> findByOdontologo_IdOdontologoAndFechaBetween(
            Long idOdontologo, LocalDate desde, LocalDate hasta
    );
    List<Turno> findByPaciente_IdPacienteAndEstado(Long idPaciente, String estado);

    @Query("""
        SELECT COUNT(t) > 0 FROM Turno t
        WHERE t.odontologo.idOdontologo = :idOdontologo
        AND t.fecha = :fecha
        AND t.hora = :hora
        AND t.estado != 'CANCELADO'
    """)
    boolean existsTurnoSolapado(
            @Param("idOdontologo") Long idOdontologo,
            @Param("fecha") LocalDate fecha,
            @Param("hora") LocalTime hora
    );

    boolean existsByOdontologo_IdOdontologoAndFechaAndHora(Long idOdontologo, @NotNull(message = "La fecha es obligatoria") @FutureOrPresent(message = "La fecha no puede ser en el pasado") LocalDate fecha, @NotNull(message = "La hora es obligatoria") LocalTime hora);

    List<Turno> findByFecha(LocalDate fecha);

    boolean existsByOdontologo_IdOdontologoAndFechaAndHoraAndIdTurnoNot(Long idOdontologo, @NotNull(message = "La fecha es obligatoria") @FutureOrPresent(message = "La fecha no puede ser en el pasado") LocalDate fecha, @NotNull(message = "La hora es obligatoria") LocalTime hora, Long id);
}

