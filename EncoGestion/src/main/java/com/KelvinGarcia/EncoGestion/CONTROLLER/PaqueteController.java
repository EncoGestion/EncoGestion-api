package com.KelvinGarcia.EncoGestion.CONTROLLER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.PaqueteRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.PaqueteResponseDTO;
import com.KelvinGarcia.EncoGestion.SERVICE.PaqueteService;
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
    public ResponseEntity<PaqueteResponseDTO> addPaquete(@Validated @RequestBody PaqueteRequestDTO paqueteRequestDTO, @PathVariable Long id) {
        PaqueteResponseDTO paquete = paqueteService.registrarPaquete(paqueteRequestDTO, id);
        return new ResponseEntity<>(paquete, HttpStatus.CREATED);
    }

}
