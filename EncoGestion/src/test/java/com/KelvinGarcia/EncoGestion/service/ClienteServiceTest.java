package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.ClienteMapper;
import com.KelvinGarcia.EncoGestion.model.dto.ClienteResponseCompletoDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Cliente;
import com.KelvinGarcia.EncoGestion.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ClienteMapper clienteMapper;
    @InjectMocks
    private ClienteService clienteService;

    @Test
    public void testCambiarContraseñaClienteExiste(){

        String id = "12345";
        Cliente cliente = new Cliente();
        cliente.setId(id);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        String contraseña = "12345";
        cliente.setContrasenia(contraseña);

        when(clienteRepository.save(cliente)).thenReturn(cliente);

        String contraseñaNueva = "56789";
        ClienteResponseCompletoDTO clienteResponseCompletoDTO = new ClienteResponseCompletoDTO();

        when(clienteMapper.convertToCompletoDTO(cliente)).thenReturn(clienteResponseCompletoDTO);

        ClienteResponseCompletoDTO response = clienteService.cambiarContraseña(id, contraseñaNueva);

        assertNotNull(response);
        assertEquals(clienteResponseCompletoDTO, response);
    }

    @Test
    public void testCambiarContraseñaClienteNoExiste(){

        String id = "12345";
        String contraseña = "12345";

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clienteService.cambiarContraseña(id, contraseña));
    }

}
