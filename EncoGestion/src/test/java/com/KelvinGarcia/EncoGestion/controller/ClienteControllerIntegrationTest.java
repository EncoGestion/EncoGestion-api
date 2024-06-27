package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.model.dto.ClienteRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SesionDTO;
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
        String contraseña = "SAMUEL1985";
        mockMvc.perform(MockMvcRequestBuilders.patch("/clientes/{id}", "60928285")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contraseña))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testEditarPerfil() throws Exception {
        ClienteRequestDTO clienteActualizado = new ClienteRequestDTO();
        clienteActualizado.setCorreo("samuel2003@gmail.com");
        clienteActualizado.setTelefono("920205522");

        mockMvc.perform(MockMvcRequestBuilders.put("/clientes/{id}", "60928285")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clienteActualizado)))
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

        clienteRequestDTO.setId("18726351");
        clienteRequestDTO.setNombre("Emerson");
        clienteRequestDTO.setContrasenia("emerson");
        clienteRequestDTO.setCorreo("emerson11@gmail.com");
        clienteRequestDTO.setTelefono("928736674");

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clienteRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testActualizarDatos() throws Exception {
            Cliente clienteActualizado = new Cliente();
            clienteActualizado.setCorreo("samuel1988@gmail.com");
            clienteActualizado.setTelefono("920203365");

            mockMvc.perform(MockMvcRequestBuilders.put("/clientes/{id}", "60928285")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(clienteActualizado)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testIniciarSesion() throws Exception{
        SesionDTO sesionDTO = new SesionDTO();
        sesionDTO.setCorreo("fiore28@example.com");
        sesionDTO.setContraseña("fiorella");

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(sesionDTO)))
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
