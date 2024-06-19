package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.EstadoYaAsignadoException;
import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.EncomiendaMapper;
import com.KelvinGarcia.EncoGestion.model.dto.*;
import com.KelvinGarcia.EncoGestion.model.entity.Cliente;
import com.KelvinGarcia.EncoGestion.model.entity.Encomienda;
import com.KelvinGarcia.EncoGestion.model.entity.Repartidor;
import com.KelvinGarcia.EncoGestion.repository.ClienteRepository;
import com.KelvinGarcia.EncoGestion.repository.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.repository.RepartidorRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EncomiendaServiceTest {

    @Mock
    private EncomiendaRepository encomiendaRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private RepartidorRepository repartidorRepository;
    @Mock
    private EncomiendaMapper encomiendaMapper;
    @InjectMocks
    private EncomiendaService encomiendaService;
    @Mock
    private PaqueteService paqueteService;
    @Mock
    private SobreService sobreService;
    @Mock
    private EntityManager entityManager;

    @Test
    public void testObtenerEncomiendasDelClienteId_EncomiendaExiste(){

        String id = "12345";

        Encomienda encomienda1 = new Encomienda();
        Encomienda encomienda2 = new Encomienda();
        List<Encomienda> encomiendas = Arrays.asList(encomienda1, encomienda2);

        when(encomiendaRepository.obtenerEncomiendasDelCliente(id)).thenReturn(encomiendas);

        PaqueteResponseDTO paquete1 = new PaqueteResponseDTO();
        PaqueteResponseDTO paquete2 = new PaqueteResponseDTO();
        List<PaqueteResponseDTO> paquetes = Arrays.asList(paquete1, paquete2);

        when(paqueteService.devolverPaquetes(encomienda1)).thenReturn(paquetes);
        when(paqueteService.devolverPaquetes(encomienda2)).thenReturn(paquetes);

        SobreResponseDTO sobre1 = new SobreResponseDTO();
        SobreResponseDTO sobre2 = new SobreResponseDTO();
        List<SobreResponseDTO> sobres = Arrays.asList(sobre1, sobre2);

        when(sobreService.devolverSobres(encomienda1)).thenReturn(sobres);
        when(sobreService.devolverSobres(encomienda2)).thenReturn(sobres);

        EncomiendaHistorialDTO historialDTO1 = new EncomiendaHistorialDTO();
        EncomiendaHistorialDTO historialDTO2 = new EncomiendaHistorialDTO();
        List<EncomiendaHistorialDTO> encomiendaHistorialDTOs = Arrays.asList(historialDTO1, historialDTO2);

        when(encomiendaRepository.obtenerEncomiendasDelCliente(id)).thenReturn(encomiendas);

        List<EncomiendaHistorialDTO> encomiendaHistorialDTOS = new ArrayList<>();
        EncomiendaHistorialDTO encomiendaHistorialDTO1 = new EncomiendaHistorialDTO();
        EncomiendaHistorialDTO encomiendaHistorialDTO2 = new EncomiendaHistorialDTO();
        encomiendaHistorialDTOS.add(encomiendaHistorialDTO1);
        encomiendaHistorialDTOS.add(encomiendaHistorialDTO2);

        when(encomiendaMapper.convertToHistorialDTO(encomienda1, paquetes, sobres)).thenReturn(historialDTO1);
        when(encomiendaMapper.convertToHistorialDTO(encomienda2, paquetes, sobres)).thenReturn(historialDTO2);

        List<EncomiendaHistorialDTO> resultado = encomiendaService.obtenerEncomiendasDelClienteId(id);

        assertNotNull(resultado);
        assertEquals(encomiendaHistorialDTOs, resultado);
    }


    @Test
    public void testObtenerEncomiendasDelClienteId_EncomiendaNoExiste(){

        String id = "12345";
        when(encomiendaRepository.obtenerEncomiendasDelCliente(id)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> encomiendaService.obtenerEncomiendasDelClienteId(id));
    }

    @Test
    public void testObtenerEncomiendasDelRepartidorId_EncomiendaExiste(){

        String id = "12345";

        Encomienda encomienda1 = new Encomienda();
        Encomienda encomienda2 = new Encomienda();
        List<Encomienda> encomiendas = Arrays.asList(encomienda1, encomienda2);

        when(encomiendaRepository.obtenerEncomiendasDelRepartidor(id)).thenReturn(encomiendas);

        PaqueteResponseDTO paquete1 = new PaqueteResponseDTO();
        PaqueteResponseDTO paquete2 = new PaqueteResponseDTO();
        List<PaqueteResponseDTO> paquetes = Arrays.asList(paquete1, paquete2);

        when(paqueteService.devolverPaquetes(encomienda1)).thenReturn(paquetes);
        when(paqueteService.devolverPaquetes(encomienda2)).thenReturn(paquetes);

        SobreResponseDTO sobre1 = new SobreResponseDTO();
        SobreResponseDTO sobre2 = new SobreResponseDTO();
        List<SobreResponseDTO> sobres = Arrays.asList(sobre1, sobre2);

        when(sobreService.devolverSobres(encomienda1)).thenReturn(sobres);
        when(sobreService.devolverSobres(encomienda2)).thenReturn(sobres);

        EncomiendaHistorialDTO historialDTO1 = new EncomiendaHistorialDTO();
        EncomiendaHistorialDTO historialDTO2 = new EncomiendaHistorialDTO();
        List<EncomiendaHistorialDTO> encomiendaHistorialDTOs = Arrays.asList(historialDTO1, historialDTO2);

        when(encomiendaRepository.obtenerEncomiendasDelRepartidor(id)).thenReturn(encomiendas);

        List<EncomiendaHistorialDTO> encomiendaHistorialDTOS = new ArrayList<>();
        EncomiendaHistorialDTO encomiendaHistorialDTO1 = new EncomiendaHistorialDTO();
        EncomiendaHistorialDTO encomiendaHistorialDTO2 = new EncomiendaHistorialDTO();
        encomiendaHistorialDTOS.add(encomiendaHistorialDTO1);
        encomiendaHistorialDTOS.add(encomiendaHistorialDTO2);

        when(encomiendaMapper.convertToHistorialDTO(encomienda1, paquetes, sobres)).thenReturn(historialDTO1);
        when(encomiendaMapper.convertToHistorialDTO(encomienda2, paquetes, sobres)).thenReturn(historialDTO2);

        List<EncomiendaHistorialDTO> resultado = encomiendaService.obtenerEncomiendasDelRepartidorId(id);

        assertNotNull(resultado);
        assertEquals(encomiendaHistorialDTOs, resultado);
    }


    @Test
    public void testObtenerEncomiendasDelRepartidorId_EncomiendaNoExiste(){

        String id = "12345";
        when(encomiendaRepository.obtenerEncomiendasDelRepartidor(id)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> encomiendaService.obtenerEncomiendasDelRepartidorId(id));
    }

    @Test
    public void testRegistrarEncomiendaClienteExiste(){
        String id = "12345";
        Cliente cliente = new Cliente();
        cliente.setId(id);

        when(clienteRepository.buscarPorId(id)).thenReturn(cliente);

        EncomiendaRequestDTO encomiendaRequestDTO = new EncomiendaRequestDTO();
        Encomienda encomienda = new Encomienda();

        when(encomiendaMapper.convertToEntity(encomiendaRequestDTO)).thenReturn(encomienda);

        EncomiendaResponseDTO encomiendaResponseDTO = new EncomiendaResponseDTO();

        when(encomiendaMapper.convertToDTO(encomienda)).thenReturn(encomiendaResponseDTO);

        EncomiendaResponseDTO response = encomiendaService.registrarEncomienda(encomiendaRequestDTO, id);

        assertNotNull(response);
        assertEquals(encomiendaResponseDTO, response);
    }

    @Test
    public void testRegistrarEncomiendaClienteNoExiste(){

    String id = "12345";
    EncomiendaRequestDTO encomiendaRequestDTO = new EncomiendaRequestDTO();

    when(clienteRepository.buscarPorId(id)).thenReturn(null);

    assertThrows(ResourceNotFoundException.class, () -> encomiendaService.registrarEncomienda(encomiendaRequestDTO, id));
    }

    @Test
    public void testBuscarEncomiendasDeClientePorFecha(){

        String id = "12345";
        Cliente cliente = new Cliente();
        cliente.setId(id);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        LocalDate fecha = LocalDate.of(2024, 06, 01);
        Encomienda encomienda1 = new Encomienda();
        Encomienda encomienda2 = new Encomienda();
        List<Encomienda> encomiendas = Arrays.asList(encomienda1, encomienda2);

        when(encomiendaRepository.obtenerEncomiendaPorFecha_ClienteID(fecha, cliente)).thenReturn(encomiendas);

        PaqueteResponseDTO paquete1 = new PaqueteResponseDTO();
        PaqueteResponseDTO paquete2 = new PaqueteResponseDTO();
        List<PaqueteResponseDTO> paquetes = Arrays.asList(paquete1, paquete2);

        when(paqueteService.devolverPaquetes(encomienda1)).thenReturn(paquetes);
        when(paqueteService.devolverPaquetes(encomienda2)).thenReturn(paquetes);

        SobreResponseDTO sobre1 = new SobreResponseDTO();
        SobreResponseDTO sobre2 = new SobreResponseDTO();
        List<SobreResponseDTO> sobres = Arrays.asList(sobre1, sobre2);

        when(sobreService.devolverSobres(encomienda1)).thenReturn(sobres);
        when(sobreService.devolverSobres(encomienda2)).thenReturn(sobres);

        EncomiendaHistorialDTO encomiendaHistorialDTO1 = new EncomiendaHistorialDTO();
        EncomiendaHistorialDTO encomiendaHistorialDTO2 = new EncomiendaHistorialDTO();

        when(encomiendaMapper.convertToHistorialDTO(encomienda1, paquetes, sobres)).thenReturn(encomiendaHistorialDTO1);
        when(encomiendaMapper.convertToHistorialDTO(encomienda2, paquetes, sobres)).thenReturn(encomiendaHistorialDTO2);

        List<EncomiendaHistorialDTO> resultado = encomiendaService.buscarEncomiendaDeClientePorFecha(fecha, id);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(encomiendaHistorialDTO1, resultado.get(0));
        assertEquals(encomiendaHistorialDTO2, resultado.get(1));

    }

    @Test
    public void testBuscarEncomiendaDeClientePorFechaClienteNoExiste(){

        String id = "12345";

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        LocalDate fecha = LocalDate.of(2024, 06, 01);

        assertThrows(ResourceNotFoundException.class, ()-> encomiendaService.buscarEncomiendaDeClientePorFecha(fecha, id));

    }

    @Test
    public void testBuscarEncomiendaDeClientePorFechaNoExisteEncomienda() {

        String id = "12345";
        Cliente cliente = new Cliente();
        cliente.setId(id);

        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));

        LocalDate fecha = LocalDate.of(2024, 06, 01);

        when(encomiendaRepository.obtenerEncomiendaPorFecha_ClienteID(fecha, cliente)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> encomiendaService.buscarEncomiendaDeClientePorFecha(fecha, id));

    }

    @Test
    public void testBuscarEncomiendasDeRepartidorPorFecha(){

        String id = "12345";
        Repartidor repartidor = new Repartidor();
        repartidor.setId(id);

        when(repartidorRepository.findById(id)).thenReturn(Optional.of(repartidor));

        LocalDate fecha = LocalDate.of(2024, 06, 01);
        Encomienda encomienda1 = new Encomienda();
        Encomienda encomienda2 = new Encomienda();
        List<Encomienda> encomiendas = Arrays.asList(encomienda1, encomienda2);

        when(encomiendaRepository.obtenerEncomiendaPorFecha_RepartidorID(fecha, repartidor)).thenReturn(encomiendas);
  
        PaqueteResponseDTO paquete1 = new PaqueteResponseDTO();
        PaqueteResponseDTO paquete2 = new PaqueteResponseDTO();
        List<PaqueteResponseDTO> paquetes = Arrays.asList(paquete1, paquete2);

        when(paqueteService.devolverPaquetes(encomienda1)).thenReturn(paquetes);
        when(paqueteService.devolverPaquetes(encomienda2)).thenReturn(paquetes);

        SobreResponseDTO sobre1 = new SobreResponseDTO();
        SobreResponseDTO sobre2 = new SobreResponseDTO();
        List<SobreResponseDTO> sobres = Arrays.asList(sobre1, sobre2);

        when(sobreService.devolverSobres(encomienda1)).thenReturn(sobres);
        when(sobreService.devolverSobres(encomienda2)).thenReturn(sobres);
  
        EncomiendaHistorialDTO encomiendaHistorialDTO1 = new EncomiendaHistorialDTO();
        EncomiendaHistorialDTO encomiendaHistorialDTO2 = new EncomiendaHistorialDTO();

        when(encomiendaMapper.convertToHistorialDTO(encomienda1, paquetes, sobres)).thenReturn(encomiendaHistorialDTO1);
        when(encomiendaMapper.convertToHistorialDTO(encomienda2, paquetes, sobres)).thenReturn(encomiendaHistorialDTO2);

        List<EncomiendaHistorialDTO> resultado = encomiendaService.buscarEncomiendaDeRepartidorPorFecha(fecha, id);

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(encomiendaHistorialDTO1, resultado.get(0));
        assertEquals(encomiendaHistorialDTO2, resultado.get(1));
  
    }
  
    @Test
    public void testBuscarEncomiendaDeRepartidorPorFechaRepartidorNoExiste(){

        String id = "12345";

        when(repartidorRepository.findById(id)).thenReturn(Optional.empty());

        LocalDate fecha = LocalDate.of(2024, 06, 01);

        assertThrows(ResourceNotFoundException.class, ()-> encomiendaService.buscarEncomiendaDeRepartidorPorFecha(fecha, id));

    }

    @Test
    public void testBuscarEncomiendaDeRepartidorPorFechaNoExisteEncomienda() {

        String id = "12345";
        Repartidor repartidor = new Repartidor();
        repartidor.setId(id);

        when(repartidorRepository.findById(id)).thenReturn(Optional.of(repartidor));

        LocalDate fecha = LocalDate.of(2024, 06, 01);

        when(encomiendaRepository.obtenerEncomiendaPorFecha_RepartidorID(fecha, repartidor)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> encomiendaService.buscarEncomiendaDeRepartidorPorFecha(fecha, id));
      
    }

    @Test
    public void testAsignarEncomienda(){

        String id_repartidor = "12345";
        String nombre = "Samuel";
        Repartidor repartidor = new Repartidor();
        repartidor.setId(id_repartidor);
        repartidor.setNombre(nombre);

        when(repartidorRepository.findById(id_repartidor)).thenReturn(Optional.of(repartidor));

        String proOrigen = "Trujillo";
        Encomienda encomienda1 = new Encomienda();
        encomienda1.setId(1L);
        Encomienda encomienda2 = new Encomienda();
        encomienda2.setId(2L);
        List<Encomienda> encomiendas = Arrays.asList(encomienda1, encomienda2);

        when(encomiendaRepository.buscarEncomiendasParaAsignar(proOrigen)).thenReturn(encomiendas);

        PaqueteResponseDTO paquete1 = new PaqueteResponseDTO();
        PaqueteResponseDTO paquete2 = new PaqueteResponseDTO();
        List<PaqueteResponseDTO> paquetes = Arrays.asList(paquete1, paquete2);

        when(paqueteService.devolverPaquetes(encomienda1)).thenReturn(paquetes);
        when(paqueteService.devolverPaquetes(encomienda2)).thenReturn(paquetes);

        SobreResponseDTO sobre1 = new SobreResponseDTO();
        SobreResponseDTO sobre2 = new SobreResponseDTO();
        List<SobreResponseDTO> sobres = Arrays.asList(sobre1, sobre2);

        when(sobreService.devolverSobres(encomienda1)).thenReturn(sobres);
        when(sobreService.devolverSobres(encomienda2)).thenReturn(sobres);
      
        when(encomiendaRepository.buscarEncomiendaID(1L)).thenReturn(encomienda1);
        when(encomiendaRepository.buscarEncomiendaID(2L)).thenReturn(encomienda2);

        EncomiendaHistorialDTO encomiendaDTO1 = new EncomiendaHistorialDTO();
        EncomiendaHistorialDTO encomiendaDTO2 = new EncomiendaHistorialDTO();
        List<EncomiendaHistorialDTO> encomiendaHistorialDTOS = Arrays.asList(encomiendaDTO1, encomiendaDTO2);

        when(encomiendaMapper.convertToHistorialDTO(encomienda1, paquetes, sobres)).thenReturn(encomiendaDTO1);
        when(encomiendaMapper.convertToHistorialDTO(encomienda2, paquetes, sobres)).thenReturn(encomiendaDTO2);

        List<EncomiendaHistorialDTO> result = encomiendaService.asignarEncomienda(proOrigen, id_repartidor);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(encomiendaDTO1, result.get(0));
        assertEquals(encomiendaDTO2, result.get(1));

    }

    @Test
    public void testAsignarEncomiendaNoExisteRepartidor(){

        String id_repartidor= "12345";

        when(repartidorRepository.findById(id_repartidor)).thenReturn(Optional.empty());

        String proOrigen = "Trujillo";

        assertThrows(ResourceNotFoundException.class, () -> encomiendaService.asignarEncomienda(proOrigen, id_repartidor));
    }

    @Test
    public void testAsignarEncomiendaNoExisteEncomienda() {

        String id_repartidor = "12345";
        Repartidor repartidor = new Repartidor();
        repartidor.setId(id_repartidor);

        when(repartidorRepository.findById(id_repartidor)).thenReturn(Optional.of(repartidor));

        String proOrigen = "Trujillo";

        when(encomiendaRepository.buscarEncomiendasParaAsignar(proOrigen)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> encomiendaService.asignarEncomienda(proOrigen, id_repartidor));
    }
  
    @Test
    public void testNotificarAutomaticamente_ExisteId_EstadoNoAsignado() {

        Encomienda encomienda = new Encomienda();
        encomienda.setId(1L);
        encomienda.setEstado("Pendiente");
        encomienda.setDepOrigen("DepOrigen");
        encomienda.setProOrigen("ProOrigen");
        encomienda.setDisOrigen("DisOrigen");
        encomienda.setDepDestino("DepDestino");
        encomienda.setProDestino("ProDestino");
        encomienda.setDisDestino("DisDestino");

        Cliente clienteRemitente = new Cliente();
        clienteRemitente.setCorreo("example@example.com");
        encomienda.setClienteRemitente(clienteRemitente);

        when(encomiendaRepository.findById(1L)).thenReturn(Optional.of(encomienda));

        String contenidoCorreo = "Su encomienda con numero de tracking: 1 con origen: DepOrigen, ProOrigen, DisOrigen y destino: DepDestino, ProDestino, DisDestino estÃ¡ En camino";
        String correo = "example@example.com";

        EncomiendaGmailDTO expectedDto = new EncomiendaGmailDTO();
        when(encomiendaMapper.convertToGmailDTO(contenidoCorreo, correo)).thenReturn(expectedDto);

        EncomiendaGmailDTO resultado = encomiendaService.notificarAutomaticamente(1L, "En camino");

        assertNotNull(resultado);
        assertEquals(expectedDto, resultado);
    }

    @Test
    public void testNotificarAutomaticamente_ExisteId_EstadoAsignado(){

        Encomienda encomienda = new Encomienda();
        encomienda.setId(1L);
        encomienda.setEstado("Pendiente");

        when(encomiendaRepository.findById(1L)).thenReturn(Optional.of(encomienda));

        assertThrows(EstadoYaAsignadoException.class, () -> encomiendaService.notificarAutomaticamente(1L, "Pendiente"));

    }

    @Test
    public void testNotificarAutomaticamente_NoExisteId(){

        Long id = 1L;
        Encomienda encomienda = new Encomienda();
        encomienda.setId(id);

        when(encomiendaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()->encomiendaService.notificarAutomaticamente(id, "En camino"));

    }

    @Test
    public void testConsultarEstado_NoExisteId(){
        Long id = 1L;
        Encomienda encomienda = new Encomienda();
        encomienda.setId(id);

        when(encomiendaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()->encomiendaService.consultarEstado(id));
    }

    @Test
    public void testConsultarEstado_ExisteId(){
        Long id = 1L;
        String expectedEstado = "Entregado";    //Aquí puede ser Entregado, En camino o En recepción

        Encomienda encomienda = new Encomienda();
        encomienda.setId(id);
        encomienda.setEstado(expectedEstado);

        when(encomiendaRepository.findById(id)).thenReturn(Optional.of(encomienda));

        String actualEstado = encomiendaService.consultarEstado(id);

        assertEquals(expectedEstado, actualEstado);
    }

}
