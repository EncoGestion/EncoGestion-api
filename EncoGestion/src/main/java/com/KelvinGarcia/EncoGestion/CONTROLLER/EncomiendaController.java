package com.KelvinGarcia.EncoGestion.CONTROLLER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.ActualizarEstadoEncomiendaDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.EncomiendaHistorialDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.EncomiendaResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import com.KelvinGarcia.EncoGestion.SERVICE.EncomiendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/encomiendas")
@AllArgsConstructor
public class EncomiendaController {

    private final EncomiendaService encomiendaService;

    @GetMapping
    public ResponseEntity<List<EncomiendaResponseDTO>> getAllEncomiendas() {
        List<EncomiendaResponseDTO> encomiendas = encomiendaService.getAllEncomiendas();
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<List<EncomiendaHistorialDTO>> getAllEncomiendasByCliente(@PathVariable String id) {
        List<EncomiendaHistorialDTO> encomiendas = encomiendaService.getEncomiendasByClienteId(id);
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

    @GetMapping("/repartidores/{id}")
    public ResponseEntity<List<EncomiendaHistorialDTO>> getAllEncomiendasByRepartidor(@PathVariable String id) {
        List<EncomiendaHistorialDTO> encomiendas = encomiendaService.getEncomiendasByRepartidorId(id);
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

    @GetMapping("/clientes/fecha/{clienteID}")
    public ResponseEntity<List<EncomiendaResponseDTO>> bucarEncomiendasPorCliente(@PathVariable String clienteID, @RequestParam("fecha") LocalDate fecha) {
        List<EncomiendaResponseDTO> encomiendas = encomiendaService.buscarEncomiendaDeClientePorFecha(fecha, clienteID);
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Encomienda> actualizarEstado(@PathVariable Long id, @RequestBody ActualizarEstadoEncomiendaDTO actualizarEstadoDTO) {
        Encomienda encomiendaActualizada = encomiendaService.actualizarEstado(id, actualizarEstadoDTO.getEstado());
        return ResponseEntity.ok(encomiendaActualizada);
    }

    @GetMapping("/repartidores/asignacion/{proOrigen}")
    public ResponseEntity<List<EncomiendaResponseDTO>> asignarEncomiendasPorProOrigen(@PathVariable String proOrigen, @RequestParam("estado") String estado, @RequestParam("id_repartidor") String id_repartidor) {
        List<EncomiendaResponseDTO> encomiendas = encomiendaService.asignarEncomienda(proOrigen, estado, id_repartidor);
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

}

