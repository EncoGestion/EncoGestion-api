package com.KelvinGarcia.EncoGestion.REPOSITORY;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RepartidorRepository extends JpaRepository<Repartidor, String> {

    Optional<Repartidor> findById(String id);

    @Query("SELECT r FROM Repartidor r WHERE r.correo=:correoRepartidor")
    Repartidor inicioSesionRepartidor(@Param("correoRepartidor") String correoRepartidor);
}
