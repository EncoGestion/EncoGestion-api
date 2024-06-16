package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.model.dto.*;
import com.KelvinGarcia.EncoGestion.service.EncomiendaService;
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
    public ResponseEntity<List<EncomiendaHistorialDTO>> obtenerEncomiendasDelCliente(@PathVariable String id) {
        List<EncomiendaHistorialDTO> encomiendas = encomiendaService.obtenerEncomiendasDelClienteId(id);
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

    @GetMapping("/repartidores/{id}")
    public ResponseEntity<List<EncomiendaHistorialDTO>> obtenerEncomiendasDelRepartidor(@PathVariable String id) {
        List<EncomiendaHistorialDTO> encomiendas = encomiendaService.obtenerEncomiendasDelRepartidorId(id);
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
    public ResponseEntity<EncomiendaGmailDTO> notificarAutomaticamente(@PathVariable Long id, @RequestBody ActualizarEstadoEncomiendaDTO actualizarEstadoDTO) {
        EncomiendaGmailDTO encomiendaActualizada = encomiendaService.notificarAutomaticamente(id, actualizarEstadoDTO.getEstado());
        return ResponseEntity.ok(encomiendaActualizada);
    }

    @GetMapping("/repartidores/asignacion/{idRepartidor}")
    public ResponseEntity<List<EncomiendaHistorialDTO>> asignarEncomiendasPorProOrigen(@PathVariable String idRepartidor, @RequestParam("proOrigen") String proOrigen) {
        List<EncomiendaHistorialDTO> encomiendas = encomiendaService.asignarEncomienda(proOrigen, idRepartidor);
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

    @PostMapping("/{idClienteRemitente}")
    public ResponseEntity<EncomiendaResponseDTO> crearEncomienda(@Validated @RequestBody EncomiendaRequestDTO encomiendaRequestDTO, @PathVariable String idClienteRemitente){
        EncomiendaResponseDTO encomienda = encomiendaService.registrarEncomienda(encomiendaRequestDTO, idClienteRemitente);
        return new ResponseEntity<>(encomienda, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/consultar")
    public ResponseEntity<String> consultarEstado(@PathVariable Long id) {
        String estado = encomiendaService.obtenerEstado(id);
        return ResponseEntity.ok(estado);
    }
}

