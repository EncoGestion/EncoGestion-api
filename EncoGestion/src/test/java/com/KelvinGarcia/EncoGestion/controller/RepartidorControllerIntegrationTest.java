package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.model.dto.RepartidorRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SesionDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Repartidor;
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
public class    RepartidorControllerIntegrationTest {

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

    @Test
    public void testCambiarContraseña() throws Exception {
        String contraseña = "SAMUEL";
        mockMvc.perform(MockMvcRequestBuilders.patch("/repartidores/{id}", "95624875")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contraseña))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testLogin() throws Exception {
        SesionDTO sesionDTO = new SesionDTO();
        sesionDTO.setCorreo("repartidor@gmail.com");
        sesionDTO.setContraseña("REPARTIDOR");

        mockMvc.perform(MockMvcRequestBuilders.get("/repartidores/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sesionDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEliminarRepartidor() throws Exception{
        String id = "95624875";

        mockMvc.perform(MockMvcRequestBuilders.delete("/repartidores/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEditarPerfil() throws Exception {
        Repartidor repartidorActualizado = new Repartidor();
        repartidorActualizado.setTelefono("956748211");

        mockMvc.perform(MockMvcRequestBuilders.put("/repartidores/{id}", "32988754")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(repartidorActualizado)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
