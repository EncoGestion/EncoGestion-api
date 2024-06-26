package com.KelvinGarcia.EncoGestion.repository;

import com.KelvinGarcia.EncoGestion.model.entity.Cliente;
import com.KelvinGarcia.EncoGestion.model.entity.Encomienda;
import com.KelvinGarcia.EncoGestion.model.entity.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
public interface EncomiendaRepository extends JpaRepository<Encomienda, Long> {


    @Query("SELECT e FROM Encomienda e WHERE e.id=:encomiendaID")
    Encomienda buscarEncomiendaID(@Param("encomiendaID") Long encomiendaID);

    @Query("SELECT e FROM Encomienda e JOIN e.clienteRemitente c WHERE c.id = :clienteID")
    List<Encomienda> obtenerEncomiendasDelCliente(@Param("clienteID") String clienteID);

    @Query("SELECT e FROM Encomienda e JOIN e.repartidor r WHERE r.id = :repartidorID")
    List<Encomienda> obtenerEncomiendasDelRepartidor(@Param("repartidorID") String repartidorID);

    @Query("SELECT e FROM Encomienda  e WHERE e.fecha=:fecha AND e.clienteRemitente=:cliente ")
    List<Encomienda> obtenerEncomiendaPorFecha_ClienteID(@Param("fecha") LocalDate fecha, @Param("cliente") Cliente cliente);

    @Query("SELECT e FROM Encomienda  e WHERE e.fecha=:fecha AND e.repartidor=:repartidor ")
    List<Encomienda> obtenerEncomiendaPorFecha_RepartidorID(@Param("fecha") LocalDate fecha, @Param("repartidor") Repartidor repartidor);

    @Query("SELECT e FROM Encomienda e WHERE e.proOrigen = :proOrigen AND e.estado = 'Por enviar' AND e.proDestino = " +
            "(SELECT sub.proDestino FROM Encomienda sub WHERE sub.proOrigen = :proOrigen AND sub.estado = 'Por enviar' " +
            "GROUP BY sub.proDestino HAVING COUNT(sub.proDestino) = " +
            "(SELECT MAX(counted) FROM (SELECT COUNT(innerSub.proDestino) AS counted FROM Encomienda innerSub " +
            "WHERE innerSub.proOrigen = :proOrigen AND innerSub.estado = 'Por enviar' GROUP BY innerSub.proDestino)))")
    List<Encomienda> buscarEncomiendasParaAsignar(@Param("proOrigen") String proOrigen);

    @Modifying
    @Query("UPDATE Encomienda e SET e.repartidor = :repartidor, e.estado = 'En camino' WHERE e.id = :id")
    void actualizarRepartidor(@Param("id") Long id, @Param("repartidor") Repartidor repartidor);

}
