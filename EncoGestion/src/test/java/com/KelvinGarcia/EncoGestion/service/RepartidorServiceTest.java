package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.RepartidorMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorSesionDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Repartidor;
import com.KelvinGarcia.EncoGestion.REPOSITORY.RepartidorRepository;
import com.KelvinGarcia.EncoGestion.SERVICE.RepartidorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepartidorServiceTest {

    @Mock
    private RepartidorRepository repartidorRepository;
    @Mock
    private RepartidorMapper repartidorMapper;
    @InjectMocks
    private RepartidorService repartidorService;

    @Test
    public void testCrearRepartidor(){

        Repartidor repartidor = new Repartidor();
        RepartidorRequestDTO reqDTO = new RepartidorRequestDTO();

        when(repartidorMapper.convertToEntiTy(reqDTO)).thenReturn(repartidor);

        RepartidorResponseDTO repartidorResponseDTO  = new RepartidorResponseDTO();

        when(repartidorMapper.convertToDTO(repartidor)).thenReturn(repartidorResponseDTO);

        RepartidorResponseDTO result = repartidorService.crearRepartidor(reqDTO);

        assertNotNull(result);
        assertEquals(result, repartidorResponseDTO);
    }

    @Test
    public void testInicioSesionRepartidorExiste(){
        RepartidorSesionDTO repartidorSesionDTO = new RepartidorSesionDTO();
        String correo = "example@example.com";
        String contraseña = "contraseña";
        repartidorSesionDTO.setCorreo(correo);
        repartidorSesionDTO.setContraseña(contraseña);
        Repartidor repartidor = new Repartidor();
        repartidor.setCorreo(correo);
        repartidor.setContrasenia(contraseña);

        when(repartidorRepository.inicioSesionRepartidor(repartidorSesionDTO.getCorreo())).thenReturn(repartidor);

        Boolean result = repartidorService.inicioSesionRepartidor(repartidorSesionDTO);

        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testInicioSesionRepartidorNoExiste(){

        RepartidorSesionDTO repartidorSesionDTO = new RepartidorSesionDTO();
        String correo = "example@example.com";
        repartidorSesionDTO.setCorreo(correo);

        when(repartidorRepository.inicioSesionRepartidor(repartidorSesionDTO.getCorreo())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> repartidorService.inicioSesionRepartidor(repartidorSesionDTO));

    }

    @Test
    public void testCambiarContraseñaRepartidorExiste(){

        String id = "12345";
        Repartidor repartidor = new Repartidor();
        repartidor.setId(id);

        when(repartidorRepository.findById(id)).thenReturn(Optional.of(repartidor));

        String contraseña = "12345";
        repartidor.setContrasenia(contraseña);

        when(repartidorRepository.save(repartidor)).thenReturn(repartidor);

        String contraseñaNueva = "56789";

        Repartidor response = repartidorService.cambiarContraseña(id, contraseñaNueva);

        assertNotNull(response);
        assertEquals(repartidor, response);
    }

    @Test
    public void testCambiarContraseñaRepartidorNoExiste(){

        String id = "12345";
        String contraseña = "12345";

        when(repartidorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> repartidorService.cambiarContraseña(id, contraseña));
    }

}
