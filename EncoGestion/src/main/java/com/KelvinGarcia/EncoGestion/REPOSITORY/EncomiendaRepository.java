package com.KelvinGarcia.EncoGestion.REPOSITORY;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
public interface EncomiendaRepository extends JpaRepository<Encomienda, Long> {

    @Query("SELECT e FROM Encomienda e WHERE e.id=:encomiendaID")
    Encomienda findBySourceOrEncomiendaID(@Param("encomiendaID") Long encomiendaID);

    @Query("SELECT e.fecha, COUNT(e) FROM Encomienda  e WHERE e.fecha BETWEEN :fechaInicio AND :fechaFin AND (e.id=:encomiendaID) GROUP BY e.fecha")
    List<Object[]> getEncomiendaByDateRangeAndEncomiendaID(@Param("fechaInicio") LocalDate fechaInicio,
                                                           @Param("fechaFin") LocalDate fechaFin,
                                                           @Param("encomiendaID") Long encomiendaID);

    @Query("SELECT e FROM Encomienda e JOIN e.cliente c WHERE c.id = :clienteID")
    List<Encomienda> getEncomiendaFromCliente(@Param("clienteID") String clienteID);

    @Query("SELECT e FROM Encomienda e JOIN e.repartidor r WHERE r.id = :repartidorID")
    List<Encomienda> getEncomiendaFromRepartidor(@Param("repartidorID") String repartidorID);
}
