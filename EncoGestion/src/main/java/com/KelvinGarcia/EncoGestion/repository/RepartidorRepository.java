package com.KelvinGarcia.EncoGestion.repository;

import com.KelvinGarcia.EncoGestion.model.entity.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepartidorRepository extends JpaRepository<Repartidor, String> {

    @Query("SELECT r FROM Repartidor r WHERE r.correo=:correoRepartidor")
    Repartidor inicioSesionRepartidor(@Param("correoRepartidor") String correoRepartidor);
}
