package com.KelvinGarcia.EncoGestion.MODEL.DTO;

import jakarta.persistence.Access;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteSesionDTO {
    @NotBlank(message = "El correo no puede estar vacio")
    @Email
    private String correo;
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String contrasenia;

}