package com.KelvinGarcia.EncoGestion.controller;
import com.KelvinGarcia.EncoGestion.model.dto.*;
import com.KelvinGarcia.EncoGestion.model.entity.Repartidor;
import com.KelvinGarcia.EncoGestion.service.RepartidorService;
import jakarta.validation.Valid;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable String id) {
        repartidorService.eliminar(id);
        return ResponseEntity.ok("Cuenta de repartidor eliminada con éxito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<RepartidorResponseCompletoDTO> editarPerfil(@PathVariable String id, @Valid @RequestBody EditarRepartidorRequestDTO repartidorActualizado) {
        RepartidorResponseCompletoDTO repartidor = repartidorService.editarPerfil(id, repartidorActualizado);
        return new ResponseEntity<>(repartidor, HttpStatus.OK);
    }

}