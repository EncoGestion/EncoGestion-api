package com.KelvinGarcia.EncoGestion.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EditarRepartidorRequestDTO {
    @Email(message = "El correo debe ser válido")
    private String correo;

    @Size(min=6, max=9, message = "El numero de telefono es de 6 a 9 caracteres")
    @Pattern(regexp = "[0-9]+", message = "El numero de telefono debe contener solo digitos")
    private String telefono;
}