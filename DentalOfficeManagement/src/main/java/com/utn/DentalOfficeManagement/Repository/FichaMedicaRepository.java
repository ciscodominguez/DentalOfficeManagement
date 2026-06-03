package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.FichaMedica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;
import java.util.Optional;

@Repository
public interface FichaMedicaRepository extends JpaRepository<FichaMedica, Long> {
    boolean existsByPaciente_IdPaciente(Long idPaciente);

    Optional<FichaMedica> findByPaciente_IdPaciente(Long idPaciente);
}
