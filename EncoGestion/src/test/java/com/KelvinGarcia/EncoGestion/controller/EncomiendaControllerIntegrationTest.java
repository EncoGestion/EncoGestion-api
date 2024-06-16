package com.KelvinGarcia.EncoGestion.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
}
