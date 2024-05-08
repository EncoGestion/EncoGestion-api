package com.KelvinGarcia.EncoGestion.REPOSITORY;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,String> {

}
