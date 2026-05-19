package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    List<Plan> findByObraSocial_IdObraSocial(Integer idObraSocial);
    List<Plan> findByNombreContainingIgnoreCase(String nombre);
    boolean existsByNombreAndObraSocial_IdObraSocial(String nombre, Integer idObraSocial);
}

