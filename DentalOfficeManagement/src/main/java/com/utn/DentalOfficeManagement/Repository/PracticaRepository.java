package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Practica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PracticaRepository extends JpaRepository<Practica, Integer> {
    
    Optional<Practica> findByCodigoInterno(String codigoInterno);
}

