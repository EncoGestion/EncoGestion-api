package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.model.dto.SobreRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SobreResponseDTO;
import com.KelvinGarcia.EncoGestion.service.SobreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sobres")
@AllArgsConstructor
public class SobreController {

    private final SobreService sobreService;

    @PostMapping("{id}")
    public ResponseEntity<SobreResponseDTO> registrarSobre(@PathVariable Long id, @Validated @RequestBody SobreRequestDTO sobreRequestDTO) {
        SobreResponseDTO sobre = sobreService.registrarSobre(sobreRequestDTO, id);
        return new ResponseEntity<>(sobre, HttpStatus.CREATED);
    }
}
