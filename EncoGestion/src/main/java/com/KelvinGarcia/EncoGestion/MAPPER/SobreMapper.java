package com.KelvinGarcia.EncoGestion.MAPPER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.SobreResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Sobre;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SobreMapper {

    private final ModelMapper modelMapper;

    public Sobre convertToEntity(SobreResponseDTO sobreResponseDTO){
        return modelMapper.map(sobreResponseDTO, Sobre.class);
    }

    public SobreResponseDTO convertToDTO(Sobre sobre){
        return modelMapper.map(sobre, SobreResponseDTO.class);
    }

    public List<SobreResponseDTO> convertToListDTO(List<Sobre> sobres){
        return sobres.stream().map(this::convertToDTO).toList();
    }
}
