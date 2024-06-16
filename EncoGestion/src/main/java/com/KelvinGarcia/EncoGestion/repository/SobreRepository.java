package com.KelvinGarcia.EncoGestion.repository;

import com.KelvinGarcia.EncoGestion.model.entity.Encomienda;
import com.KelvinGarcia.EncoGestion.model.entity.Sobre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SobreRepository extends JpaRepository<Sobre, Long> {

    @Query("SELECT s FROM Sobre s WHERE s.encomienda= :encomienda")
    List<Sobre> listaDeSobre(@Param("encomienda") Encomienda encomienda);

    @Query("SELECT s FROM Sobre s WHERE s.id= :id")
    Sobre buscarSobreId(@Param("id")Long id);
}
