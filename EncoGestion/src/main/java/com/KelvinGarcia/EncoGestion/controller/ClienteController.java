package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.model.dto.*;
import com.KelvinGarcia.EncoGestion.model.entity.Cliente;
import com.KelvinGarcia.EncoGestion.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/clientes")
@AllArgsConstructor

public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> crearCuenta(@Valid @RequestBody
                                                          ClienteRequestDTO clienteDTO){
    ClienteResponseDTO cuentaCreada = clienteService.crearCuenta(clienteDTO);
    return new ResponseEntity<>(cuentaCreada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> editarPerfil(@PathVariable String id,
                                                              @Valid @RequestBody EditarClienteRequestDTO clienteRequestDTO){
        ClienteResponseDTO clienteActualizadoResultado = clienteService.editarPerfil(id, clienteRequestDTO);
        return new ResponseEntity<>(clienteActualizadoResultado, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity<ClienteResponseCompletoDTO> cambiarContraseña(@PathVariable String id, @RequestBody String contraseña){
        ClienteResponseCompletoDTO cliente = clienteService.cambiarContraseña(id, contraseña);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody SesionDTO clienteSesionDTO) {
        try {
            boolean sesion = clienteService.iniciarSesionCliente(clienteSesionDTO);
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }

    }

}
