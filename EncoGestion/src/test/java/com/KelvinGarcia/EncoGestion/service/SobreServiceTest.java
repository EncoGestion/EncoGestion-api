package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.SobreMapper;
import com.KelvinGarcia.EncoGestion.model.dto.SobreRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SobreResponseDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Encomienda;
import com.KelvinGarcia.EncoGestion.model.entity.Sobre;
import com.KelvinGarcia.EncoGestion.repository.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.repository.SobreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SobreServiceTest {

    @Mock
    private SobreRepository sobreRepository;
    @Mock
    private EncomiendaRepository encomiendaRepository;
    @Mock
    private SobreMapper sobreMapper;
    @InjectMocks
    private SobreService sobreService;

    @Test
    public void testRegistrarSobreEncomiendaExiste(){
        Long id = 1L;
        Encomienda encomienda = new Encomienda();
        encomienda.setId(id);

        when(encomiendaRepository.buscarEncomiendaID(id)).thenReturn(encomienda);

        Sobre sobre = new Sobre();
        SobreRequestDTO sobreRequestDTO  = new SobreRequestDTO();

        when(sobreMapper.convertToEntity(sobreRequestDTO)).thenReturn(sobre);

        SobreResponseDTO sobreResponseDTO  = new SobreResponseDTO();

        when(sobreMapper.convertToDTO(sobre)).thenReturn(sobreResponseDTO);

        SobreResponseDTO result = sobreService.registrarSobre(sobreRequestDTO, id);

        assertNotNull(result);
        assertEquals(sobreResponseDTO, result);
    }

    @Test
    public void testRegistrarSobreEncomiendaNoExiste(){
        Long id = 1L;

        when(encomiendaRepository.buscarEncomiendaID(id)).thenReturn(null);

        SobreRequestDTO sobreRequestDTO  = new SobreRequestDTO();

        assertThrows(ResourceNotFoundException.class, () -> sobreService.registrarSobre(sobreRequestDTO ,id));
    }
}
