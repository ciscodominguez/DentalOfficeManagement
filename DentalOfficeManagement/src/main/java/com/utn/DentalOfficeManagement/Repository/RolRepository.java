package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    boolean existsByNombre(String nombre);
    boolean existsByNombreAndIdRolNot(String nombre, Integer id);
    Optional<Rol> findByNombre(String nombre);
}
