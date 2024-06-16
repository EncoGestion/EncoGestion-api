package com.KelvinGarcia.EncoGestion.CONTROLLER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.*;
import com.KelvinGarcia.EncoGestion.SERVICE.EncomiendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/encomiendas")
@AllArgsConstructor
public class EncomiendaController {

    private final EncomiendaService encomiendaService;

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
    public ResponseEntity<List<EncomiendaHistorialDTO>> bucarEncomiendasDeClientePorFecha(@PathVariable String clienteID, @RequestParam("fecha") LocalDate fecha) {
        List<EncomiendaHistorialDTO> encomiendas = encomiendaService.buscarEncomiendaDeClientePorFecha(fecha, clienteID);
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

    @GetMapping("/repartidores/fecha/{repartidorID}")
    public ResponseEntity<List<EncomiendaHistorialDTO>> bucarEncomiendasDeRepartidorPorFecha(@PathVariable String repartidorID, @RequestParam("fecha") LocalDate fecha) {
        List<EncomiendaHistorialDTO> encomiendas = encomiendaService.buscarEncomiendaDeRepartidorPorFecha(fecha, repartidorID);
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<EncomiendaGmailDTO> actualizarEstado(@PathVariable Long id, @RequestBody ActualizarEstadoEncomiendaDTO actualizarEstadoDTO) {
        EncomiendaGmailDTO encomiendaActualizada = encomiendaService.actualizarEstado(id, actualizarEstadoDTO.getEstado());
        return ResponseEntity.ok(encomiendaActualizada);
    }

    @GetMapping("/repartidores/asignacion/{proOrigen}")
    public ResponseEntity<List<EncomiendaHistorialDTO>> asignarEncomiendasPorProOrigen(@PathVariable String proOrigen, @RequestParam("estado") String estado, @RequestParam("id_repartidor") String id_repartidor) {
        List<EncomiendaHistorialDTO> encomiendas = encomiendaService.asignarEncomienda(proOrigen, estado, id_repartidor);
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EncomiendaResponseDTO> crearEncomienda(@Validated @RequestBody EncomiendaRequestDTO encomiendaRequestDTO, @RequestParam("clienteRemitente") String clienteRemitente){
        EncomiendaResponseDTO encomienda = encomiendaService.registrarEncomienda(encomiendaRequestDTO, clienteRemitente);
        return new ResponseEntity<>(encomienda, HttpStatus.CREATED);
    }
}

