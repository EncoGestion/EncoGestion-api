package com.KelvinGarcia.EncoGestion.REPOSITORY;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
public interface RepartidorRepository extends JpaRepository<Repartidor, Long> {

    @Query("SELECT r FROM Repartidor r WHERE r.id=:repartidorID")
    List<Repartidor> buscarRepartidorPorID(@Param("repartidorID") Long repartidorID);
    @Query("SELECT r FROM Repartidor r WHERE r.nombre=:nombreRepartidor")
    Repartidor inicioSesionRepartidor(@Param("nombreRepartidor") String nombreRepartidor);
}
