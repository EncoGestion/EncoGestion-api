package com.KelvinGarcia.EncoGestion.MODEL.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncomiendaGmailDTO {

    private String correo;
    private String contenidoCorreo;
}
