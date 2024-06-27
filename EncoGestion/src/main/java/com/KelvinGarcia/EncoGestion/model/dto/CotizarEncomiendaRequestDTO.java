package com.KelvinGarcia.EncoGestion.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class CotizarEncomiendaRequestDTO {
    @NotBlank(message = "El departamento de origen no puede estar vacío")
    private String depOrigen;

    @NotBlank(message = "El provincia de origen no puede estar vacío")
    private String proOrigen;

    @NotBlank(message = "El distrito de origen no puede estar vacío")
    private String disOrigen;

    @NotBlank(message = "El departamento de destino no puede estar vacío")
    private String depDestino;

    @NotBlank(message = "El provincia de destino no puede estar vacío")
    private String proDestino;

    @NotBlank(message = "El distrito de destino no puede estar vacío")
    private String disDestino;

    @NotNull(message = "El tipo de encomienda no puede estar vacía")
    private String tipoEncomienda;

    @NotNull(message = "El peso no puede estar vacío")
    private double peso;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double altura;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double ancho;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private double largo;
}