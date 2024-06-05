package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.EXCEPTION.EstadoYaAsignadoException;
import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.EncomiendaMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.EncomiendaGmailDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import com.KelvinGarcia.EncoGestion.REPOSITORY.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.SERVICE.EncomiendaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void testActualizarEstado_ExisteId_EstadoNoAsignado() {

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

        EncomiendaGmailDTO resultado = encomiendaService.actualizarEstado(1L, "En camino");

        assertNotNull(resultado);
        assertEquals(expectedDto, resultado);
    }

    @Test
    public void testActualizarEstado_ExisteId_EstadoAsignado(){

        Encomienda encomienda = new Encomienda();
        encomienda.setId(1L);
        encomienda.setEstado("Pendiente");

        when(encomiendaRepository.findById(1L)).thenReturn(Optional.of(encomienda));

        assertThrows(EstadoYaAsignadoException.class, () -> encomiendaService.actualizarEstado(1L, "Pendiente"));

    }

    @Test
    public void testActualizarEstado_NoExisteId(){

        Long id = 1L;
        Encomienda encomienda = new Encomienda();
        encomienda.setId(id);

        when(encomiendaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, ()->encomiendaService.actualizarEstado(id, "En camino"));
    }

}