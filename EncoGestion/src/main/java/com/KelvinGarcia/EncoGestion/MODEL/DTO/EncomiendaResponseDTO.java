package com.KelvinGarcia.EncoGestion.MODEL.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncomiendaResponseDTO {

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
    private String clienteDestiantario;
    private ClienteResponseDTO  clienteRemitente;
    private RepartidorResponseDTO repartidor;
}
