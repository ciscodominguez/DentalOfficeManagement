package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    List<Rol> findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
