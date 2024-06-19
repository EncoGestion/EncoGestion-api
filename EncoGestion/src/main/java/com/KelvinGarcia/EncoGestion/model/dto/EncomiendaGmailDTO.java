package com.KelvinGarcia.EncoGestion.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncomiendaGmailDTO {

    private String correo;
    private String contenidoCorreo;
}