package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.ClienteMapper;
import com.KelvinGarcia.EncoGestion.model.dto.ClienteResponseCompletoDTO;
import com.KelvinGarcia.EncoGestion.model.dto.ClienteResponseDTO;
import com.KelvinGarcia.EncoGestion.model.dto.EditarClienteRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.*;
import com.KelvinGarcia.EncoGestion.model.entity.Cliente;
import com.KelvinGarcia.EncoGestion.repository.ClienteRepository;
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
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ClienteMapper clienteMapper;
    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId("1");
        cliente.setNombre("Juan");
        cliente.setContrasenia("juan2763");
        cliente.setCorreo("juanv87@example.com");
        cliente.setTelefono("927361823");
    }

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

    @Test
    public void testEliminarCliente_NoExiste(){
        String id = "43177628";

        when(clienteRepository.existsById(id)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> clienteService.eliminarCliente(id));
    }

    @Test
    public void testEliminarCliente_Existe(){
        String id = "43188722";

        when(clienteRepository.existsById(id)).thenReturn(true);

        clienteService.eliminarCliente(id);
    }

    @Test
    public void testCrearCliente(){
        Cliente cliente = new Cliente();
        ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO();

        when(clienteMapper.convertToEntiTy(clienteRequestDTO)).thenReturn(cliente);

        ClienteResponseDTO clienteResponseDTO  = new ClienteResponseDTO();

        when(clienteMapper.convertToDTO(cliente)).thenReturn(clienteResponseDTO);

        ClienteResponseDTO result = clienteService.crearCuenta(clienteRequestDTO);

        assertNotNull(result);
        assertEquals(result, clienteResponseDTO);
    }

  @Test
    public void testActualizacionCompleta() {
        EditarClienteRequestDTO clienteActualizadoDTO = new EditarClienteRequestDTO();
        clienteActualizadoDTO.setCorreo("juan02@example.com");
        clienteActualizadoDTO.setTelefono("927638192");

        Cliente clienteActualizadoEntity = new Cliente();
        clienteActualizadoEntity.setId("1");
        clienteActualizadoEntity.setNombre("Juan");
        clienteActualizadoEntity.setContrasenia("juan276");
        clienteActualizadoEntity.setCorreo("juan02@example.com");
        clienteActualizadoEntity.setTelefono("927638192");

        ClienteResponseDTO clienteActualizadoDTOResponse = new ClienteResponseDTO();
        clienteActualizadoDTOResponse.setId("1");
        clienteActualizadoDTOResponse.setNombre("Juan");
        clienteActualizadoDTOResponse.setCorreo("juan02@example.com");
        clienteActualizadoDTOResponse.setTelefono("927638192");

        when(clienteRepository.findById("1")).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteActualizadoEntity);
        when(clienteMapper.convertToDTO(any(Cliente.class))).thenReturn(clienteActualizadoDTOResponse);

        ClienteResponseDTO resultado = clienteService.editarPerfil("1", clienteActualizadoDTO);

        assertNotNull(resultado);
        assertEquals("juan02@example.com", resultado.getCorreo());
        assertEquals("927638192", resultado.getTelefono());
    }

    @Test
    public void testActualizacionParcial() {
        EditarClienteRequestDTO clienteActualizadoDTO = new EditarClienteRequestDTO();
        clienteActualizadoDTO.setTelefono("926371823");

        Cliente clienteActualizadoEntity = new Cliente();
        clienteActualizadoEntity.setId("1");
        clienteActualizadoEntity.setNombre("Juan");
        clienteActualizadoEntity.setContrasenia("juan2763");
        clienteActualizadoEntity.setCorreo("juanv87@example.com");
        clienteActualizadoEntity.setTelefono("926371823");

        ClienteResponseDTO clienteActualizadoDTOResponse = new ClienteResponseDTO();
        clienteActualizadoDTOResponse.setId("1");
        clienteActualizadoDTOResponse.setNombre("Juan");
        clienteActualizadoDTOResponse.setContrasenia("juan2763");
        clienteActualizadoDTOResponse.setCorreo("juanv87@example.com");
        clienteActualizadoDTOResponse.setTelefono("926371823");

        when(clienteRepository.findById("1")).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteActualizadoEntity);
        when(clienteMapper.convertToDTO(any(Cliente.class))).thenReturn(clienteActualizadoDTOResponse);

        ClienteResponseDTO resultado = clienteService.editarPerfil("1", clienteActualizadoDTO);

        assertNotNull(resultado);
        assertEquals("juan2763", resultado.getContrasenia());
        assertEquals("juanv87@example.com", resultado.getCorreo());
        assertEquals("926371823", resultado.getTelefono());
    }

    @Test
    public void testActualizarDatos_ClienteNoEncontrado() {
        EditarClienteRequestDTO clienteActualizado = new EditarClienteRequestDTO();
        clienteActualizado.setTelefono("926735127");
      
        when(clienteRepository.findById("1")).thenReturn(Optional.empty());
      
        assertThrows(ResourceNotFoundException.class, () -> clienteService.editarPerfil("1", clienteActualizado));
    }

    @Test
    public void testIniciarSesionCliente_Existe(){
        SesionDTO sesionDTO = new SesionDTO();
        String correo = "juan@example.com";
        String contraseña = "valerio";
        sesionDTO.setCorreo(correo);
        sesionDTO.setContraseña(contraseña);
        Cliente cliente = new Cliente();
        cliente.setCorreo(correo);
        cliente.setContrasenia(contraseña);

        when(clienteRepository.iniciarSesionCliente(sesionDTO.getCorreo())).thenReturn(cliente);

        Boolean result = clienteService.iniciarSesionCliente(sesionDTO);

        assertNotNull(result);
        assertTrue(result);
    }

    @Test
    public void testIniciarSesionCliente_ContraseñaIncorrecta(){
        SesionDTO sesionDTO = new SesionDTO();
        String correo = "juan@example.com";
        String contraseña = "valerio";
        sesionDTO.setCorreo(correo);
        sesionDTO.setContraseña(contraseña);

        Cliente cliente = new Cliente();
        cliente.setCorreo(correo);
        cliente.setContrasenia("valerio123");        

        when(clienteRepository.iniciarSesionCliente(sesionDTO.getCorreo())).thenReturn(cliente);

        assertThrows(ResourceNotFoundException.class, () -> clienteService.iniciarSesionCliente(sesionDTO));
    }
}
