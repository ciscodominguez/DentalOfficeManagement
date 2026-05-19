package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
}

