package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.EncomiendaMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.EncomiendaResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import com.KelvinGarcia.EncoGestion.REPOSITORY.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.SERVICE.EncomiendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EncomiendaServiceTest {

    @Mock
    private EncomiendaRepository encomiendaRepository;
    @Mock
    private EncomiendaMapper encomiendaMapper;
    @InjectMocks
    private EncomiendaService encomiendaService;

    @Test
    public void testGetEncomiendasByClienteId_EncomiendaExiste(){

        String id = "12345";
        Encomienda encomienda1 = new Encomienda();
        Encomienda encomienda2 = new Encomienda();
        List<Encomienda> encomiendas = new ArrayList<>();
        encomiendas.add(encomienda1);
        encomiendas.add(encomienda2);

        when(encomiendaRepository.getEncomiendaFromCliente(id)).thenReturn(encomiendas);

        List<EncomiendaResponseDTO> encomiendaResponseDTOS = new ArrayList<>();
        EncomiendaResponseDTO encomiendaResponseDTO1 = new EncomiendaResponseDTO();
        EncomiendaResponseDTO encomiendaResponseDTO2 = new EncomiendaResponseDTO();
        encomiendaResponseDTOS.add(encomiendaResponseDTO1);
        encomiendaResponseDTOS.add(encomiendaResponseDTO2);

        when(encomiendaMapper.convertToListDTO(encomiendas)).thenReturn(encomiendaResponseDTOS);

        List<EncomiendaResponseDTO> resultado = encomiendaService.getEncomiendasByClienteId(id);

        assertNotNull(resultado);
        assertEquals(encomiendaResponseDTOS, resultado);
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
        List<Encomienda> encomiendas = new ArrayList<>();
        encomiendas.add(encomienda1);
        encomiendas.add(encomienda2);

        when(encomiendaRepository.getEncomiendaFromRepartidor(id)).thenReturn(encomiendas);

        List<EncomiendaResponseDTO> encomiendaResponseDTOS = new ArrayList<>();
        EncomiendaResponseDTO encomiendaResponseDTO1 = new EncomiendaResponseDTO();
        EncomiendaResponseDTO encomiendaResponseDTO2 = new EncomiendaResponseDTO();
        encomiendaResponseDTOS.add(encomiendaResponseDTO1);
        encomiendaResponseDTOS.add(encomiendaResponseDTO2);

        when(encomiendaMapper.convertToListDTO(encomiendas)).thenReturn(encomiendaResponseDTOS);

        List<EncomiendaResponseDTO> resultado = encomiendaService.getEncomiendasByRepartidorId(id);

        assertNotNull(resultado);
        assertEquals(encomiendaResponseDTOS, resultado);
    }

    @Test
    public void testGetEncomiendasByRepartidorId_EncomiendaNoExiste(){

        String id = "12345";
        when(encomiendaRepository.getEncomiendaFromRepartidor(id)).thenReturn(Collections.emptyList());

        assertThrows(ResourceNotFoundException.class, () -> encomiendaService.getEncomiendasByRepartidorId(id));
    }
}
