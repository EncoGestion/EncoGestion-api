package com.KelvinGarcia.EncoGestion.MODEL.DTO;

import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaqueteResponseDTO {

    private Long id;
    private float ancho;
    private float alto;
    private float largo;
    private float peso;
}
