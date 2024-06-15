package com.KelvinGarcia.EncoGestion.REPOSITORY;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Repartidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
public interface EncomiendaRepository extends JpaRepository<Encomienda, Long> {


    @Query("SELECT e FROM Encomienda e WHERE e.id=:encomiendaID")
    Encomienda findBySourceOrEncomiendaID(@Param("encomiendaID") Long encomiendaID);

    @Query("SELECT e FROM Encomienda e JOIN e.clienteRemitente c WHERE c.id = :clienteID")
    List<Encomienda> getEncomiendaFromCliente(@Param("clienteID") String clienteID);

    @Query("SELECT e FROM Encomienda e JOIN e.repartidor r WHERE r.id = :repartidorID")
    List<Encomienda> getEncomiendaFromRepartidor(@Param("repartidorID") String repartidorID);

    @Query("SELECT e FROM Encomienda  e WHERE e.fecha=:fecha AND e.clienteRemitente=:cliente ")
    List<Encomienda> getEncomiendaByDateAndClienteID(@Param("fecha") LocalDate fecha, @Param("cliente") Cliente cliente);

    @Query("SELECT e FROM Encomienda e WHERE e.proOrigen = :proOrigen AND e.estado = :estado AND e.proDestino = " +
            "(SELECT sub.proDestino FROM Encomienda sub WHERE sub.proOrigen = :proOrigen AND sub.estado = :estado " +
            "GROUP BY sub.proDestino HAVING COUNT(sub.proDestino) = " +
            "(SELECT MAX(counted) FROM (SELECT COUNT(innerSub.proDestino) AS counted FROM Encomienda innerSub " +
            "WHERE innerSub.proOrigen = :proOrigen AND innerSub.estado = :estado GROUP BY innerSub.proDestino)))")
    List<Encomienda> buscarEncomiendasParaAsignar(@Param("proOrigen") String proOrigen, @Param("estado") String estado );

    @Modifying
    @Query("UPDATE Encomienda e SET e.repartidor = :repartidor, e.estado = 'En camino' WHERE e.id = :id")
    void updateRepartidor(@Param("id") Long id, @Param("repartidor") Repartidor repartidor);

}
