package com.KelvinGarcia.EncoGestion.REPOSITORY;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EncomiendaRepository extends JpaRepository<Encomienda, Long> {

    @Query("SELECT e FROM Encomienda  e WHERE e.fecha=:fecha AND e.cliente=:cliente ")
    List<Encomienda> getEncomiendaByDateAndClienteID(@Param("fecha") LocalDate fecha, @Param("cliente") Cliente cliente);
}
