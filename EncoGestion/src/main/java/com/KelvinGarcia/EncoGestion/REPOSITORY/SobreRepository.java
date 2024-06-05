package com.KelvinGarcia.EncoGestion.REPOSITORY;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Sobre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SobreRepository extends JpaRepository<Sobre, Long> {

    @Query("SELECT s FROM Sobre s WHERE s.encomienda= :encomienda")
    List<Sobre> listaDeSobre(@Param("encomienda") Encomienda encomienda);
}