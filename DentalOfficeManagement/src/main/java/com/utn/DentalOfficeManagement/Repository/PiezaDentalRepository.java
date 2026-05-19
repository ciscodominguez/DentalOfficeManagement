package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.PiezaDental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PiezaDentalRepository extends JpaRepository<PiezaDental, Integer> {
}

