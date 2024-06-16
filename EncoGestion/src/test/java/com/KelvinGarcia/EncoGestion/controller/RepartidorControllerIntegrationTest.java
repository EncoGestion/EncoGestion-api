package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class RepartidorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCrearRepartidor() throws Exception {
        RepartidorRequestDTO repartidorRequestDTO = new RepartidorRequestDTO();
        repartidorRequestDTO.setId("32988754");
        repartidorRequestDTO.setNombre("Repartidor");
        repartidorRequestDTO.setContrasenia("REPARTIDOR");
        repartidorRequestDTO.setTelefono("966352587");
        repartidorRequestDTO.setCorreo("repartidor@gmail.com");
        repartidorRequestDTO.setEstado("Libre");
        repartidorRequestDTO.setUbiProvincia("Lima");
        mockMvc.perform(MockMvcRequestBuilders.post("/repartidores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(repartidorRequestDTO)))
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
