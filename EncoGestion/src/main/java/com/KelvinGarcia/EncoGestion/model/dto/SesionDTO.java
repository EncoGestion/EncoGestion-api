package com.KelvinGarcia.EncoGestion.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SesionDTO {


    @NotBlank(message = "El correo no puede estar vacio")
    @Email
    private String correo;
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String contraseña;
}
