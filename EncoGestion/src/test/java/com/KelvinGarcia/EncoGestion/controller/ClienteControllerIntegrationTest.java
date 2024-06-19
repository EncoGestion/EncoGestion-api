package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.model.dto.ClienteRequestDTO;

import com.KelvinGarcia.EncoGestion.model.entity.Cliente;
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

    @Test

    public void testEliminarCliente() throws Exception{
        String id = "18726351";

        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCrearCliente() throws Exception{
        ClienteRequestDTO clienteRequestDTO = new ClienteRequestDTO();

        clienteRequestDTO.setId("82763512");
        clienteRequestDTO.setNombre("Kelvin");
        clienteRequestDTO.setContrasenia("kelvin");
        clienteRequestDTO.setCorreo("kelvin@gmail.com");
        clienteRequestDTO.setTelefono("928736123");

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clienteRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
  
  public void testActualizarDatos() throws Exception {
        Cliente clienteActualizado = new Cliente();
        clienteActualizado.setCorreo("samuel1985@gmail.com");
        clienteActualizado.setTelefono("920203365");

        mockMvc.perform(MockMvcRequestBuilders.put("/clientes/{id}", "60928285")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clienteActualizado)))
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
