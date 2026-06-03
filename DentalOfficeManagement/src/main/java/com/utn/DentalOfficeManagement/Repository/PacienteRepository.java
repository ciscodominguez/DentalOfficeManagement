package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    List<Paciente> findByDni(String dni);
    List<Paciente> findByNombreContainingIgnoreCase(String nombre);
    List<Paciente> findByNroAfiliado(String nroAfiliado);
    List<Paciente> findByPlan_IdPlan(Long idPlan);
    List<Paciente> findByPlan_ObraSocial_IdObraSocial(Long idObraSocial);
    boolean existsByDni(String dni);
}

