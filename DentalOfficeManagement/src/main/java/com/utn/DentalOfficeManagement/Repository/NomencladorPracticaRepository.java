package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.NomencladorPractica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface NomencladorPracticaRepository  extends JpaRepository <NomencladorPractica, Long> {
    boolean existsByObraSocial_IdObraSocialAndPractica_IdPractica(Long idObraSocial, Long idPractica);

    List<NomencladorPractica> findByObraSocial_IdObraSocial(Long idObraSocial);

    boolean existsByObraSocial_IdObraSocialAndPractica_IdPracticaAndIdNomencladorNot(Long idObraSocial, Long idPractica, Long id);
}
