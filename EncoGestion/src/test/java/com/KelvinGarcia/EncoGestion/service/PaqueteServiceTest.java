package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.PaqueteMapper;
import com.KelvinGarcia.EncoGestion.model.dto.PaqueteRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.PaqueteResponseDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Encomienda;
import com.KelvinGarcia.EncoGestion.model.entity.Paquete;
import com.KelvinGarcia.EncoGestion.repository.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.repository.PaqueteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaqueteServiceTest {

    @Mock
    private PaqueteRepository paqueteRepository;
    @Mock
    private EncomiendaRepository encomiendaRepository;
    @Mock
    private PaqueteMapper paqueteMapper;
    @InjectMocks
    private PaqueteService paqueteService;

    @Test
    public void testRegistrarPaqueteEncomiendaExiste(){
        Long id = 1L;
        Encomienda encomienda = new Encomienda();
        encomienda.setId(id);

        when(encomiendaRepository.buscarEncomiendaID(id)).thenReturn(encomienda);

        Paquete paquete = new Paquete();
        PaqueteRequestDTO paqueteRequestDTO = new PaqueteRequestDTO();

        when(paqueteMapper.convertToEntity(paqueteRequestDTO)).thenReturn(paquete);

        PaqueteResponseDTO paqueteResponseDTO = new PaqueteResponseDTO();

        when(paqueteMapper.convertToDTO(paquete)).thenReturn(paqueteResponseDTO);

        PaqueteResponseDTO result = paqueteService.registrarPaquete(paqueteRequestDTO, id);

        assertNotNull(result);
        assertEquals(paqueteResponseDTO, result);
    }

    @Test
    public void testRegistrarPaqueteEncomiendaNoExiste(){
        Long id = 1L;

        when(encomiendaRepository.buscarEncomiendaID(id)).thenReturn(null);

        PaqueteRequestDTO paqueteRequestDTO = new PaqueteRequestDTO();

        assertThrows(ResourceNotFoundException.class, () -> paqueteService.registrarPaquete(paqueteRequestDTO, id));
    }
}
