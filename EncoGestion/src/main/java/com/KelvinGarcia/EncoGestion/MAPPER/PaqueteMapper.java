package com.KelvinGarcia.EncoGestion.MAPPER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.PaqueteResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Paquete;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PaqueteMapper {

    private final ModelMapper modelMapper;

    public Paquete convertToEntity(PaqueteResponseDTO paqueteResponseDTO) {
        return modelMapper.map(paqueteResponseDTO, Paquete.class);
    }

    public PaqueteResponseDTO convertToDTO(Paquete paquete) {
        return modelMapper.map(paquete, PaqueteResponseDTO.class);
    }

    public List<PaqueteResponseDTO> convertToListDTO(List<Paquete> paquetes) {
        return paquetes.stream().map(this::convertToDTO).toList();
    }

}
