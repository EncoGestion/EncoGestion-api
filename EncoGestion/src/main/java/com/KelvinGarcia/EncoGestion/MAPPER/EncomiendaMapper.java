package com.KelvinGarcia.EncoGestion.MAPPER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.EncomiendaRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.EncomiendaResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EncomiendaMapper {

    private final ModelMapper modelMapper;

    public Encomienda convertToEntity(EncomiendaRequestDTO encomiendaRequestDTO){
        return modelMapper.map(encomiendaRequestDTO, Encomienda.class);
    }

    public EncomiendaResponseDTO convertToDTO(Encomienda encomienda){
        return modelMapper.map(encomienda, EncomiendaResponseDTO.class);
    }

    public List<EncomiendaResponseDTO> convertToListDTO(List<Encomienda> encomiendas){
        return encomiendas.stream().map(this::convertToDTO).toList();
    }
}
