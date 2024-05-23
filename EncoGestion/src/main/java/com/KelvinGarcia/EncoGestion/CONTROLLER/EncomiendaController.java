package com.KelvinGarcia.EncoGestion.CONTROLLER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.EncomiendaResponseDTO;
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

    @GetMapping("/clientes/{clienteID}")
    public ResponseEntity<List<EncomiendaResponseDTO>> bucarEncomiendasPorCliente(@PathVariable String clienteID, @RequestParam("fecha") LocalDate fecha) {
        List<EncomiendaResponseDTO> encomiendas = encomiendaService.buscarEncomiendaDeClientePorFecha(fecha, clienteID);
        return new ResponseEntity<>(encomiendas, HttpStatus.OK);
    }

}