package com.KelvinGarcia.EncoGestion.MODEL.DTO;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaqueteRequestDTO {

    @Nullable
    private Long id;
    @NotNull(message = "El ancho no puede ser vacio")
    @DecimalMin(value = "0.01", message = "La cantidad debe ser mayor que cero")
    private float ancho;
    @NotNull(message = "El alto no puede ser vacio")
    @DecimalMin(value = "0.01", message = "La cantidad debe ser mayor que cero")
    private float alto;
    @NotNull(message = "El largo no puede ser vacio")
    @DecimalMin(value = "0.01", message = "La cantidad debe ser mayor que cero")
    private float largo;
    @NotNull(message = "El peso no puede ser vacio")
    @DecimalMin(value = "0.01", message = "La cantidad debe ser mayor que cero")
    private float peso;
    @Nullable
    private Encomienda encomienda;

}
