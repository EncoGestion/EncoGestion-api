package com.KelvinGarcia.EncoGestion.mapper;

import com.KelvinGarcia.EncoGestion.model.dto.SobreRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SobreResponseDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Sobre;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SobreMapper {

    private final ModelMapper modelMapper;

    public Sobre convertToEntity(SobreRequestDTO sobreRequestDTO){
        return modelMapper.map(sobreRequestDTO, Sobre.class);
    }

    public SobreResponseDTO convertToDTO(Sobre sobre){
        return modelMapper.map(sobre, SobreResponseDTO.class);
    }

    public List<SobreResponseDTO> convertToListDTO(List<Sobre> sobres){
        return sobres.stream().map(this::convertToDTO).toList();
    }
}
