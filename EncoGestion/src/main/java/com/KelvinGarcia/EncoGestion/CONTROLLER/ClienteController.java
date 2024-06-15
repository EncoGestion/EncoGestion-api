package com.KelvinGarcia.EncoGestion.CONTROLLER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.SERVICE.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteSesionDTO;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor

public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCuenta(@RequestBody
                                                          ClienteRequestDTO clienteDTO){
    ClienteResponseDTO cuentaCreada = clienteService.crearCuenta(clienteDTO);
    return new ResponseEntity<>(cuentaCreada, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ClienteResponseDTO> actualizarDatos(@PathVariable String id,
                                                              @RequestBody ClienteRequestDTO clienteDTO){
        ClienteResponseDTO datosActualizados = clienteService.actualizarDatos(id, clienteDTO);
        return new ResponseEntity<>(datosActualizados, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Cliente> cambiarContraseña(@PathVariable String id, @RequestBody String contraseña){
        Cliente cliente = clienteService.cambiarContraseña(id, contraseña);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody ClienteSesionDTO clienteSesionDTO) {
        try {
            boolean sesion = clienteService.iniciarSesionCliente(clienteSesionDTO);
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }

    }

}
