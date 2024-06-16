package com.KelvinGarcia.EncoGestion.MODEL.DTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Repartidor;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EncomiendaRequestDTO {

    @Nullable
    private Long id;
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
    @Nullable
    private LocalDate fecha;
    @Nullable
    private LocalDate hora;
    @Nullable
    private String estado;
    @NotBlank(message = "Tienes que ingresar el DNI del destinatario")
    private String clienteDestinatario;
    @Nullable
    private Cliente clienteRemitente;
    @Nullable
    private Repartidor repartidor;
}
