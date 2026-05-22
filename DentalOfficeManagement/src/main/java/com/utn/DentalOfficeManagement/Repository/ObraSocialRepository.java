package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.ObraSocial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Integer> {
    boolean existsByCuit(String cuit);
    boolean existsByCuitAndIdObraSocialNot(String cuit, Integer id);
    List<ObraSocial> findByNombreContainingIgnoreCase(String nombre);
}
