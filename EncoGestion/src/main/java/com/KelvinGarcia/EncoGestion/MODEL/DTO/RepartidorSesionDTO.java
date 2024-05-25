package com.KelvinGarcia.EncoGestion.MODEL.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepartidorSesionDTO {


    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String contrasenia;
}
