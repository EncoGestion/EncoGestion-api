package com.KelvinGarcia.EncoGestion.controller;

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
public class ClienteControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCambiarContraseña() throws Exception {
        String contraseña = "KELVIN";
        mockMvc.perform(MockMvcRequestBuilders.patch("/clientes/{id}", "75074590")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contraseña))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
