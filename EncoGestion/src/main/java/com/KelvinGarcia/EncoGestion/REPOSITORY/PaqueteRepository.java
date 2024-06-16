package com.KelvinGarcia.EncoGestion.REPOSITORY;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Paquete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaqueteRepository extends JpaRepository<Paquete, Long> {

    @Query("SELECT p FROM Paquete p WHERE p.encomienda= :encomienda")
    List<Paquete> listaDePaquete(@Param("encomienda")Encomienda encomienda);

    @Query("SELECT p FROM Paquete p WHERE p.id= :id")
    Paquete buscarPaqueteId(@Param("id")Long id);

}
