package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
}

