package com.KelvinGarcia.EncoGestion.controller;

import com.KelvinGarcia.EncoGestion.model.dto.ActualizarEstadoEncomiendaDTO;
import com.KelvinGarcia.EncoGestion.model.dto.CotizarEncomiendaRequestDTO;
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
        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/clientes/{id}", "82763512"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testObtenerEncomiendasDelRepartidor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/repartidores/{id}", "32988754"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testBucarEncomiendasDeClientePorFecha() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/clientes/fecha/{clienteID}", "82763512")
                        .param("fecha", "2024-06-27"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testBuscarEncomiendasDeRepartidorPorFecha() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/repartidores/fecha/{repartidorID}", "32988754")
                        .param("fecha", "2024-06-27"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAsignarEncomiendasPorProOrigen() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/repartidores/asignacion/{idRepartidor}", "32988754")
                        .param("proOrigen", "Trujillo"))
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
        encomiendaRequestDTO.setClienteDestinatario("60928285");

        mockMvc.perform(MockMvcRequestBuilders.post("/encomiendas/{idClienteRemitente}", "82763512")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(encomiendaRequestDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testNotificarAutomaticamente() throws Exception {
        ActualizarEstadoEncomiendaDTO actualizarEstadoDTO = new ActualizarEstadoEncomiendaDTO();
        actualizarEstadoDTO.setEstado("Entregado");

        mockMvc.perform(MockMvcRequestBuilders.patch("/encomiendas/{id}/estado", "253201")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(actualizarEstadoDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testConsultarEstado() throws Exception{
        Long id = 253201L;

        mockMvc.perform(MockMvcRequestBuilders.get("/encomiendas/{id}/consultar", "253201"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCotizarEncomienda() throws Exception{
        CotizarEncomiendaRequestDTO encomiendaDTO = new CotizarEncomiendaRequestDTO();
        encomiendaDTO.setDepOrigen("La Libertad");
        encomiendaDTO.setProOrigen("Trujillo");
        encomiendaDTO.setDisOrigen("Trujillo");
        encomiendaDTO.setDepDestino("Lima");
        encomiendaDTO.setProDestino("Lima");
        encomiendaDTO.setDisDestino("San Borja");
        encomiendaDTO.setTipoEncomienda("paquete");
        encomiendaDTO.setPeso(2);
        encomiendaDTO.setAltura(35);
        encomiendaDTO.setAncho(40);
        encomiendaDTO.setLargo(25);

        mockMvc.perform(MockMvcRequestBuilders.post("/encomiendas/cotizar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(encomiendaDTO)))
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
