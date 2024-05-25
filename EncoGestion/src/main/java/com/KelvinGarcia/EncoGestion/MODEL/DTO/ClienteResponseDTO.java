package com.KelvinGarcia.EncoGestion.MODEL.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

    private String id;
    private String nombre;
    private String contrasenia;
    private String correo;
    private String telefono;
}
