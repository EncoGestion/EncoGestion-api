package com.KelvinGarcia.EncoGestion.CONTROLLER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteResponseDTO;
import com.KelvinGarcia.EncoGestion.SERVICE.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
