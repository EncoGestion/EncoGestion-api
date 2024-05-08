package com.KelvinGarcia.EncoGestion.MAPPER;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorSesionDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Repartidor;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@AllArgsConstructor
public class RepartidorMapper {

    private final ModelMapper modelMapper;

    public Repartidor convertToEntiTy(RepartidorRequestDTO repartidorRequestDTO){
        return modelMapper.map(repartidorRequestDTO, Repartidor.class);
    }

    public RepartidorResponseDTO convertToDTO(Repartidor repartidor){
        return modelMapper.map(repartidor, RepartidorResponseDTO.class);
    }

    public List<RepartidorResponseDTO> convertToListDTO(List<Repartidor> repartidores){
        return repartidores.stream().map(this::convertToDTO).toList();

    }
    public Repartidor convertInicio(RepartidorSesionDTO repartidorSesionDTO){
        return modelMapper.map(repartidorSesionDTO, Repartidor.class);
    }
}
