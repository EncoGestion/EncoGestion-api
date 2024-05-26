package com.KelvinGarcia.EncoGestion.MODEL.DTO;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncomiendaRequestDTO {

    @NotBlank(message = "El departamneto de origen no puede estar vacio")
    private String depOrigen;
    @NotBlank(message = "La provincia de origen no puede estar vacia")
    private String proOrigen;
    @NotBlank(message = "El distrito de origen no puede estar vacio")
    private String disOrigen;
    @NotBlank(message = "El departamento de destino no puede estar vacio")
    private String depDestino;
    @NotBlank(message = "La provincia de destino no puede estar vacia")
    private String proDestino;
    @NotBlank(message = "El distrito de destino no puede estar vacio")
    private String disDestino;
    @NotBlank(message = "La contraseña no puede estar vacia")
    private String contraseña;
    @Nullable
    private String direccion;
    @NotBlank(message = "Tienes que ingresar el DNI del destinatario")
    private String clienteDestinatario;
}
