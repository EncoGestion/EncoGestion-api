package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.RepartidorMapper;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorResponseCompletoDTO;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorResponseDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SesionDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Repartidor;
import com.KelvinGarcia.EncoGestion.repository.RepartidorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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
        SesionDTO sesionDTO = new SesionDTO();
        String correo = "example@example.com";
        String contraseña = "contraseña";
        sesionDTO.setCorreo(correo);
        sesionDTO.setContraseña(contraseña);
        Repartidor repartidor = new Repartidor();
        repartidor.setCorreo(correo);
        repartidor.setContrasenia(contraseña);

        when(repartidorRepository.inicioSesionRepartidor(sesionDTO.getCorreo())).thenReturn(repartidor);

        Boolean result = repartidorService.inicioSesionRepartidor(sesionDTO);

        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testInicioSesionRepartidorNoExiste(){

        SesionDTO sesionDTO = new SesionDTO();
        String correo = "example@example.com";
        sesionDTO.setCorreo(correo);

        when(repartidorRepository.inicioSesionRepartidor(sesionDTO.getCorreo())).thenReturn(null);

        assertThrows(ResourceNotFoundException.class, () -> repartidorService.inicioSesionRepartidor(sesionDTO));

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
        RepartidorResponseCompletoDTO repartidorResponseCompletoDTO = new RepartidorResponseCompletoDTO();

        when(repartidorMapper.convertToCompletoDTO(repartidor)).thenReturn(repartidorResponseCompletoDTO);

        RepartidorResponseCompletoDTO response = repartidorService.cambiarContraseña(id, contraseñaNueva);

        assertNotNull(response);
        assertEquals(repartidorResponseCompletoDTO, response);
    }

    @Test
    public void testCambiarContraseñaRepartidorNoExiste(){

        String id = "12345";
        String contraseña = "12345";

        when(repartidorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> repartidorService.cambiarContraseña(id, contraseña));
    }

    @Test
    public void testEliminarRepartidor_NoExisteId() {
        String id = "12345678";

        when(repartidorRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> {
            repartidorService.eliminar(id);
        });
    }

    @Test
    public void testEliminarRepartidor_ExisteId() {
        String id = "12345678";

        when(repartidorRepository.existsById(id)).thenReturn(true);

        repartidorService.eliminar(id);
    }

    @Test
    public void testEditarPerfil_RepartidorExiste() {
        String id = "12345678";
        Repartidor repartidor = new Repartidor();
        repartidor.setId(id);
        repartidor.setContrasenia("sergio123");

        Repartidor repartidorActualizado = new Repartidor();
        repartidorActualizado.setId(id);
        repartidorActualizado.setContrasenia("sergio1562");

        when(repartidorRepository.findById(id)).thenReturn(Optional.of(repartidor));
        when(repartidorRepository.save(any(Repartidor.class))).thenReturn(repartidorActualizado);

        Repartidor resultado = repartidorService.editarPerfil(id, repartidorActualizado);

        assertNotNull(resultado);
        assertEquals("sergio1562", resultado.getContrasenia());
    }

    @Test
    public void testEditarPerfil_RepartidorNoExiste() {
        String id = "12345678";
        Repartidor repartidorActualizado = new Repartidor();
        repartidorActualizado.setId(id);
        repartidorActualizado.setCorreo("sergio22@example.com");

        when(repartidorRepository.findById(id)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            repartidorService.editarPerfil(id, repartidorActualizado);
        });

        assertEquals("Repartidor no encontrado", exception.getMessage());
    }

}
