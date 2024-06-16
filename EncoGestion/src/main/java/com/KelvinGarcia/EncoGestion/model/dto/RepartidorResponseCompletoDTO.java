package com.KelvinGarcia.EncoGestion.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepartidorResponseCompletoDTO {

    private String id;
    private String nombre;
    private String contrasenia;
    private String telefono;
    private String correo;
    private String estado;
    private String ubiProvincia;

}
