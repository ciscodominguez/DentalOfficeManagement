package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.ObraSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {
    boolean existsByCuit(String cuit);
    boolean existsByCuitAndIdObraSocialNot(String cuit, Long id);
    List<ObraSocial> findByNombreContainingIgnoreCase(String nombre);
}
