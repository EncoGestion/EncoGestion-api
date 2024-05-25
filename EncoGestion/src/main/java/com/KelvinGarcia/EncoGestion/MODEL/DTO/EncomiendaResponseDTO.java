package com.KelvinGarcia.EncoGestion.MODEL.DTO;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Repartidor;
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
    private String direccion;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado;
    private String cliente;
    private String repartidor;
}
