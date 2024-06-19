package com.KelvinGarcia.EncoGestion.mapper;

import com.KelvinGarcia.EncoGestion.model.dto.PaqueteRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.PaqueteResponseDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Paquete;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PaqueteMapper {

    private final ModelMapper modelMapper;

    public Paquete convertToEntity(PaqueteRequestDTO paqueteRequestDTO) {
        return modelMapper.map(paqueteRequestDTO, Paquete.class);
    }

    public PaqueteResponseDTO convertToDTO(Paquete paquete) {
        return modelMapper.map(paquete, PaqueteResponseDTO.class);
    }

    public List<PaqueteResponseDTO> convertToListDTO(List<Paquete> paquetes) {
        return paquetes.stream().map(this::convertToDTO).toList();
    }

}
