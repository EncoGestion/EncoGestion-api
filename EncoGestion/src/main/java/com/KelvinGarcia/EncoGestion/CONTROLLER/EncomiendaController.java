package com.KelvinGarcia.EncoGestion.CONTROLLER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.EncomiendaRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.CotizacionResponseDTO;
import com.KelvinGarcia.EncoGestion.SERVICE.EncomiendaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/encomiendas")
@AllArgsConstructor
public class EncomiendaController {
    private final EncomiendaService encomiendaService;

    @PostMapping("/cotizar")
    public ResponseEntity<CotizacionResponseDTO> cotizarEncomienda(@RequestBody EncomiendaRequestDTO encomiendaDTO){
        CotizacionResponseDTO cotizacion = encomiendaService.cotizarEncomienda(encomiendaDTO);
        return new ResponseEntity<>(cotizacion, HttpStatus.OK);
    }
}
