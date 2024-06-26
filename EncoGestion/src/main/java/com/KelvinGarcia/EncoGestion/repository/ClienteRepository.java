package com.KelvinGarcia.EncoGestion.repository;

import com.KelvinGarcia.EncoGestion.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    @Query("SELECT c FROM Cliente c WHERE c.id=:clienteID")
    Cliente buscarPorId (@Param("clienteID") String clienteID);

    @Query("SELECT c FROM Cliente c WHERE c.correo=:correoCliente")
    Cliente iniciarSesionCliente(@Param("correoCliente") String correoCliente);
}

