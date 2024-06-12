package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.ClienteMapper;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.REPOSITORY.ClienteRepository;
import com.KelvinGarcia.EncoGestion.SERVICE.ClienteService;
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

        Cliente response = clienteService.cambiarContraseña(id, contraseñaNueva);

        assertNotNull(response);
        assertEquals(cliente, response);
    }

    @Test
    public void testCambiarContraseñaClienteNoExiste(){

        String id = "12345";
        String contraseña = "12345";

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clienteService.cambiarContraseña(id, contraseña));
    }

}
