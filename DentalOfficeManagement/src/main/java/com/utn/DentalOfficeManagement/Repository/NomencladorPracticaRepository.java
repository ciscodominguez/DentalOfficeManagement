package com.utn.DentalOfficeManagement.Repository;

import com.utn.DentalOfficeManagement.Model.NomencladorPractica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface NomencladorPracticaRepository  extends JpaRepository <NomencladorPractica, Integer> {
    boolean existsByObraSocial_IdObraSocialAndPractica_IdPractica(Integer idObraSocial, Integer idPractica);

    List<NomencladorPractica> findByObraSocial_IdObraSocial(Integer idObraSocial);

    boolean existsByObraSocial_IdObraSocialAndPractica_IdPracticaAndIdNomencladorNot(Integer idObraSocial, Integer idPractica, Integer id);
}
