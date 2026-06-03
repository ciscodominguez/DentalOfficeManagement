package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.CoberturaPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoberturaPlanRepository extends JpaRepository<CoberturaPlan, Long> {
    List<CoberturaPlan> findByPlan_IdPlan(Long idPlan);
    List<CoberturaPlan> findByPractica_IdPractica(Long idPractica);
    Optional<CoberturaPlan> findByPlan_IdPlanAndPractica_IdPractica(
            Long idPlan, Long idPractica
    );
    boolean existsByPlan_IdPlanAndPractica_IdPractica(
            Long idPlan, Long idPractica
    );

    boolean existsByPlan_IdPlanAndPractica_IdPracticaAndIdCoberturaPlanNot(Long idPlan, Long idPractica, Long id);
}
