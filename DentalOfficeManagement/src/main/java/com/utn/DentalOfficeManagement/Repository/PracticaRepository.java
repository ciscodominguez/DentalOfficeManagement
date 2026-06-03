package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Practica;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Long> {
    
    List<Practica> findByCodigoInterno(String codigoInterno);

    boolean existsByCodigoInterno(@NotBlank(message = "El código interno no puede estar vacío") String codigoInterno);

    List<Practica> findByNombreContainingIgnoreCase(String nombre);

    boolean existsByCodigoInternoAndIdPracticaNot(@NotBlank(message = "El código interno no puede estar vacío") String codigoInterno, Long id);
}

