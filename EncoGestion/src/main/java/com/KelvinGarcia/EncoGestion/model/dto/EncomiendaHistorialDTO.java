package com.KelvinGarcia.EncoGestion.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncomiendaHistorialDTO {

    private Long id;
    private String depOrigen;
    private String proOrigen;
    private String disOrigen;
    private String depDestino;
    private String proDestino;
    private String disDestino;
    private String contraseña;
    private String direccion;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private String clienteDestinatario;
    private ClienteResponseDTO  clienteRemitente;
    private RepartidorResponseDTO repartidor;
    private List<PaqueteResponseDTO> paquetes;
    private List<SobreResponseDTO> sobres;
}
