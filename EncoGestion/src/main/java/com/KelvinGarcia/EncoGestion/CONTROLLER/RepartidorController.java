package com.KelvinGarcia.EncoGestion.CONTROLLER;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorSesionDTO;
import com.KelvinGarcia.EncoGestion.SERVICE.RepartidorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/repartidores")
@AllArgsConstructor
public class RepartidorController {

    private final RepartidorService repartidorService;

    @GetMapping
    public ResponseEntity<List<RepartidorResponseDTO>> getAllRepartidores(){
        List<RepartidorResponseDTO> repartidores = repartidorService.getAllRepartidores();
        return new ResponseEntity<>(repartidores, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RepartidorResponseDTO> crearRepartidor(@Validated @RequestBody RepartidorRequestDTO repartidorRequestDTO){
        RepartidorResponseDTO crearRepartidor = repartidorService.crearRepartidor(repartidorRequestDTO);
        return new ResponseEntity<>(crearRepartidor, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<Boolean> login(@Validated @RequestBody RepartidorSesionDTO repartidorSesionDTO) {
        Boolean sesion = repartidorService.inicioSesionRepartidor(repartidorSesionDTO);
        return new ResponseEntity<>(sesion, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable String id) {
        repartidorService.eliminar(id);
        return ResponseEntity.ok("Cuenta de repartidor eliminada con éxito");
    }

}
