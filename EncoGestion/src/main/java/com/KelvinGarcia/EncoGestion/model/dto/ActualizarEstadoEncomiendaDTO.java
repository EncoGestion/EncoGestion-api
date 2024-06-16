package com.KelvinGarcia.EncoGestion.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ActualizarEstadoEncomiendaDTO {

    @NotNull(message = "El estado no puede estar vacío")
    private String estado;
}
