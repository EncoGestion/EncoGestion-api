package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.model.dto.PaqueteRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.PaqueteResponseDTO;
import com.KelvinGarcia.EncoGestion.service.PaqueteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paquetes")
@AllArgsConstructor
public class PaqueteController {

    private final PaqueteService paqueteService;

    @PostMapping("{id}")
    public ResponseEntity<PaqueteResponseDTO> registrarPaquete(@Validated @RequestBody PaqueteRequestDTO paqueteRequestDTO, @PathVariable Long id) {
        PaqueteResponseDTO paquete = paqueteService.registrarPaquete(paqueteRequestDTO, id);
        return new ResponseEntity<>(paquete, HttpStatus.CREATED);
    }

}
