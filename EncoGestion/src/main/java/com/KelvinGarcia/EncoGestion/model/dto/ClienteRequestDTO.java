package com.KelvinGarcia.EncoGestion.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequestDTO {

    @NotBlank(message = "El numero de DNI no puede estar vacio")
    @Size(min=8, max=8, message = "El numero de DNI es de 8 caracteres")
    @Pattern(regexp = "[0-9]+", message = "El numero de DNI debe contener solo digitos")
    private String id;
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String contrasenia;
    @NotBlank(message = "El correo no puede estar vacio")
    @Email
    private String correo;
    @NotBlank(message = "El numero de telefono no puede estar vacio")
    @Size(min=6, max=9, message = "El numero de telefono es de 6 a 9 caracteres")
    @Pattern(regexp = "[0-9]+", message = "El numero de telefono debe contener solo digitos")
    private String telefono;

}
