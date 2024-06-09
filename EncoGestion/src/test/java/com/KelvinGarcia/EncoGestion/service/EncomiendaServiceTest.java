package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.EncomiendaMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.*;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import com.KelvinGarcia.EncoGestion.REPOSITORY.ClienteRepository;
import com.KelvinGarcia.EncoGestion.REPOSITORY.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.SERVICE.EncomiendaService;
import com.KelvinGarcia.EncoGestion.SERVICE.PaqueteService;
import com.KelvinGarcia.EncoGestion.SERVICE.SobreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EncomiendaServiceTest {

    @Mock
    private EncomiendaRepository encomiendaRepository;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private EncomiendaMapper encomiendaMapper;
    @InjectMocks
    private EncomiendaService encomiendaService;
    @Mock
    private PaqueteService paqueteService;
    @Mock
    private SobreService sobreService;

    @Test
    public void testGetEncomiendasByClienteId_EncomiendaExiste(){

        String id = "12345";

        Encomienda encomienda1 = new Encomienda();
        Encomienda encomienda2 = new Encomienda();
        List<Encomienda> encomiendas = Arrays.asList(encomienda1, encomienda2);

        when(encomiendaRepository.getEncomiendaFromCliente(id)).thenReturn(encomiendas);

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

        when(encomiendaRepository.getEncomiendaFromCliente(id)).thenReturn(encomiendas);

        List<EncomiendaHistorialDTO> encomiendaHistorialDTOS = new ArrayList<>();
        EncomiendaHistorialDTO encomiendaHistorialDTO1 = new EncomiendaHistorialDTO();
        EncomiendaHistorialDTO encomiendaHistorialDTO2 = new EncomiendaHistorialDTO();
        encomiendaHistorialDTOS.add(encomiendaHistorialDTO1);
        encomiendaHistorialDTOS.add(encomiendaHistorialDTO2);

        when(encomiendaMapper.convertToHistorialDTO(encomienda1, paquetes, sobres)).thenReturn(historialDTO1);
        when(encomiendaMapper.convertToHistorialDTO(encomienda2, paquetes, sobres)).thenReturn(historialDTO2);

        List<EncomiendaHistorialDTO> resultado = encomiendaService.getEncomiendasByClienteId(id);

        assertNotNull(resultado);
        assertEquals(encomiendaHistorialDTOs, resultado);
    }


    @Test
    public void testGetEncomiendasByClienteId_EncomiendaNoExiste(){

        String id = "12345";
        when(encomiendaRepository.getEncomiendaFromCliente(id)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> encomiendaService.getEncomiendasByClienteId(id));
    }

    @Test
    public void testGetEncomiendasByRepartidorId_EncomiendaExiste(){

        String id = "12345";

        Encomienda encomienda1 = new Encomienda();
        Encomienda encomienda2 = new Encomienda();
        List<Encomienda> encomiendas = Arrays.asList(encomienda1, encomienda2);

        when(encomiendaRepository.getEncomiendaFromRepartidor(id)).thenReturn(encomiendas);

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

        when(encomiendaRepository.getEncomiendaFromRepartidor(id)).thenReturn(encomiendas);

        List<EncomiendaHistorialDTO> encomiendaHistorialDTOS = new ArrayList<>();
        EncomiendaHistorialDTO encomiendaHistorialDTO1 = new EncomiendaHistorialDTO();
        EncomiendaHistorialDTO encomiendaHistorialDTO2 = new EncomiendaHistorialDTO();
        encomiendaHistorialDTOS.add(encomiendaHistorialDTO1);
        encomiendaHistorialDTOS.add(encomiendaHistorialDTO2);

        when(encomiendaMapper.convertToHistorialDTO(encomienda1, paquetes, sobres)).thenReturn(historialDTO1);
        when(encomiendaMapper.convertToHistorialDTO(encomienda2, paquetes, sobres)).thenReturn(historialDTO2);

        List<EncomiendaHistorialDTO> resultado = encomiendaService.getEncomiendasByRepartidorId(id);

        assertNotNull(resultado);
        assertEquals(encomiendaHistorialDTOs, resultado);
    }


    @Test
    public void testGetEncomiendasByRepartidorId_EncomiendaNoExiste(){

        String id = "12345";
        when(encomiendaRepository.getEncomiendaFromRepartidor(id)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> encomiendaService.getEncomiendasByRepartidorId(id));
    }

    @Test
    public void testCrearEncomiendaClienteExiste(){
        String id = "12345";
        Cliente cliente = new Cliente();
        cliente.setId(id);

        when(clienteRepository.buscarPorId(id)).thenReturn(cliente);

        EncomiendaRequestDTO encomiendaRequestDTO = new EncomiendaRequestDTO();
        Encomienda encomienda = new Encomienda();

        when(encomiendaMapper.convertToEntity(encomiendaRequestDTO)).thenReturn(encomienda);

        EncomiendaResponseDTO encomiendaResponseDTO = new EncomiendaResponseDTO();

        when(encomiendaMapper.convertToDTO(encomienda)).thenReturn(encomiendaResponseDTO);

        EncomiendaResponseDTO response = encomiendaService.crearEncomienda(encomiendaRequestDTO, id);

        assertNotNull(response);
        assertEquals(encomiendaResponseDTO, response);
    }

    @Test
    public void testCrearEncomiendaClienteNoExiste(){

    String id = "12345";
    EncomiendaRequestDTO encomiendaRequestDTO = new EncomiendaRequestDTO();

    when(clienteRepository.buscarPorId(id)).thenReturn(null);

    assertThrows(ResourceNotFoundException.class, () -> encomiendaService.crearEncomienda(encomiendaRequestDTO, id));
    }
}
