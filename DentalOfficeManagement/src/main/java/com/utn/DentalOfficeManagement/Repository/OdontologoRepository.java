package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Odontologo;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    boolean existsByMatricula(@NotBlank(message = "La matrícula es obligatoria") String matricula);

    List<Odontologo> findByNombreContainingIgnoreCase(String nombre);

    List<Odontologo> findByEspecialidadContainingIgnoreCase(String especialidad);

    boolean existsByMatriculaAndIdOdontologoNot(@NotBlank(message = "La matrícula es obligatoria") String matricula, Long id);
}
