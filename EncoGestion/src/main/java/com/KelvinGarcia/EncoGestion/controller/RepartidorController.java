package com.KelvinGarcia.EncoGestion.controller;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorResponseCompletoDTO;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorResponseDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SesionDTO;
import com.KelvinGarcia.EncoGestion.service.RepartidorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repartidores")
@AllArgsConstructor
public class RepartidorController {

    private final RepartidorService repartidorService;

    @PostMapping
    public ResponseEntity<RepartidorResponseDTO> crearRepartidor(@Validated @RequestBody RepartidorRequestDTO repartidorRequestDTO){
        RepartidorResponseDTO crearRepartidor = repartidorService.crearRepartidor(repartidorRequestDTO);
        return new ResponseEntity<>(crearRepartidor, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> login(@Validated @RequestBody SesionDTO sesionDTO) {
        Boolean sesion = repartidorService.inicioSesionRepartidor(sesionDTO);
        return new ResponseEntity<>(sesion, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<RepartidorResponseCompletoDTO> cambiarContraseña(@PathVariable String id, @RequestBody String contraseña){
        RepartidorResponseCompletoDTO repartidor = repartidorService.cambiarContraseña(id, contraseña);
        return new ResponseEntity<>(repartidor, HttpStatus.OK);
    }

}