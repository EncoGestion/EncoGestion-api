package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.model.dto.PaqueteRequestDTO;
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
public class PaqueteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegisterPaquete() throws Exception {
        PaqueteRequestDTO paqueteRequestDTO = new PaqueteRequestDTO();
        paqueteRequestDTO.setAlto((float) 2.5);
        paqueteRequestDTO.setLargo((float) 1.5);
        paqueteRequestDTO.setAncho((float) 0.9);
        paqueteRequestDTO.setPeso((float) 12.4);

        mockMvc.perform(MockMvcRequestBuilders.post("/paquetes/{id}", "716020")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(paqueteRequestDTO)))
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
