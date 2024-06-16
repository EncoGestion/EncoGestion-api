package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.model.dto.EncomiendaRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.source.tree.LineMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class EncomiendaControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testObtenerEncomiendasDelCliente() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/clientes/{id}", "75074590"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testObtenerEncomiendasDelRepartidor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/repartidores/{id}", "95624875"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testBucarEncomiendasDeClientePorFecha() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/clientes/fecha/{clienteID}", "75074590")
                        .param("fecha", "2024-06-15"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testBuscarEncomiendasDeRepartidorPorFecha() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/repartidores/fecha/{repartidorID}", "95624875")
                        .param("fecha", "2024-06-15"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAsignarEncomiendasPorProOrigen() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/repartidores/asignacion/{idRepartidor}", "95624875")
                        .param("proOrigen", "Lima"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRegisterSobre() throws Exception {
        EncomiendaRequestDTO encomiendaRequestDTO = new EncomiendaRequestDTO();
        encomiendaRequestDTO.setDepOrigen("La Libertad");
        encomiendaRequestDTO.setProOrigen("Trujillo");
        encomiendaRequestDTO.setDisOrigen("Trujillo");
        encomiendaRequestDTO.setDepDestino("Lima");
        encomiendaRequestDTO.setProDestino("Lima");
        encomiendaRequestDTO.setDisDestino("San Borja");
        encomiendaRequestDTO.setContraseña("12345");
        encomiendaRequestDTO.setClienteDestinatario("75659832");

        mockMvc.perform(MockMvcRequestBuilders.post("/encomiendas/{idClienteRemitente}", "62539184")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(encomiendaRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
