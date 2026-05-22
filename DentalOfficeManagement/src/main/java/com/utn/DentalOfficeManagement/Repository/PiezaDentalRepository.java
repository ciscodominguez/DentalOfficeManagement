package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.PiezaDental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PiezaDentalRepository extends JpaRepository<PiezaDental, Integer> {
    List<PiezaDental> findByEstado(String estado);

    @Query("""
        SELECT pd FROM PiezaDental pd
        JOIN PracticaRealizada pr ON pr.piezaDental.idPiezaDental = pd.idPiezaDental
        JOIN Turno t ON pr.turno.idTurno = t.idTurno
        WHERE t.paciente.idPaciente = :idPaciente
    """)
    List<PiezaDental> findByPacienteId(@Param("idPaciente") Integer idPaciente);

    List<PiezaDental> findByFichaMedica_Paciente_IdPaciente(Integer idPaciente);
}
