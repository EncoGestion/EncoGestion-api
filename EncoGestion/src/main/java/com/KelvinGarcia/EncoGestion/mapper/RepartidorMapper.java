package com.KelvinGarcia.EncoGestion.mapper;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorResponseCompletoDTO;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorResponseDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SesionDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Repartidor;
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
    public Repartidor convertInicio(SesionDTO sesionDTO){
        return modelMapper.map(sesionDTO, Repartidor.class);
    }

    public RepartidorResponseCompletoDTO convertToCompletoDTO(Repartidor repartidor){
        return modelMapper.map(repartidor, RepartidorResponseCompletoDTO.class);
    }
}
