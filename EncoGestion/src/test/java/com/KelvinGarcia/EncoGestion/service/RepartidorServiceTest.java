package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.RepartidorMapper;
import com.KelvinGarcia.EncoGestion.model.dto.*;
import com.KelvinGarcia.EncoGestion.model.entity.Repartidor;
import com.KelvinGarcia.EncoGestion.repository.RepartidorRepository;
import org.junit.jupiter.api.BeforeEach;
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
    private Repartidor repartidor;
    @BeforeEach
    void setUp() {
        repartidor = new Repartidor();
        repartidor.setId("1");
        repartidor.setNombre("Juan");
        repartidor.setContrasenia("juan2763");
        repartidor.setCorreo("juanv87@example.com");
        repartidor.setTelefono("927361823");
        repartidor.setEstado("Libre");
        repartidor.setUbiProvincia("Trujillo");
    }
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
        EditarRepartidorRequestDTO repartidorActualizadoDTO = new EditarRepartidorRequestDTO();
        repartidorActualizadoDTO.setTelefono("926371823");

        Repartidor repartidorActualizadoEntity = new Repartidor();
        repartidorActualizadoEntity.setId("1");
        repartidorActualizadoEntity.setNombre("Juan");
        repartidorActualizadoEntity.setContrasenia("juan2763");
        repartidorActualizadoEntity.setCorreo("juanv87@example.com");
        repartidorActualizadoEntity.setTelefono("926371823");
        repartidorActualizadoEntity.setEstado("Libre");
        repartidorActualizadoEntity.setUbiProvincia("Trujillo");

        RepartidorResponseCompletoDTO repartidorActualizadoDTOResponse = new RepartidorResponseCompletoDTO();
        repartidorActualizadoDTOResponse.setId("1");
        repartidorActualizadoDTOResponse.setNombre("Juan");
        repartidorActualizadoDTOResponse.setContrasenia("juan2763");
        repartidorActualizadoDTOResponse.setCorreo("juanv87@example.com");
        repartidorActualizadoDTOResponse.setTelefono("926371823");
        repartidorActualizadoDTOResponse.setEstado("Libre");
        repartidorActualizadoDTOResponse.setUbiProvincia("Trujillo");

        when(repartidorRepository.findById("1")).thenReturn(Optional.of(repartidor));
        when(repartidorMapper.convertToCompletoDTO(any(Repartidor.class))).thenReturn(repartidorActualizadoDTOResponse);

        RepartidorResponseCompletoDTO resultado = repartidorService.editarPerfil("1", repartidorActualizadoDTO);

        assertNotNull(resultado);
        assertEquals("926371823", resultado.getTelefono());
    }

    @Test
    public void testEditarPerfil_RepartidorNoExiste() {
        EditarRepartidorRequestDTO repartidorActualizadoDTO = new EditarRepartidorRequestDTO();
        repartidorActualizadoDTO.setTelefono("926371823");

        when(repartidorRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> repartidorService.editarPerfil("1", repartidorActualizadoDTO));
    }

}
