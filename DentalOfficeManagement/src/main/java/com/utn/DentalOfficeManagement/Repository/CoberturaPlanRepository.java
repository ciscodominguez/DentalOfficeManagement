package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.CoberturaPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoberturaPlanRepository extends JpaRepository<CoberturaPlan, Integer> {
    List<CoberturaPlan> findByPlan_IdPlan(Integer idPlan);
    List<CoberturaPlan> findByPractica_IdPractica(Integer idPractica);
    Optional<CoberturaPlan> findByPlan_IdPlanAndPractica_IdPractica(
            Integer idPlan, Integer idPractica
    );
    boolean existsByPlan_IdPlanAndPractica_IdPractica(
            Integer idPlan, Integer idPractica
    );
}
