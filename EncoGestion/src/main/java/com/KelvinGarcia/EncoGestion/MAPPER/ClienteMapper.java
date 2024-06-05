package com.KelvinGarcia.EncoGestion.MAPPER;

import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ClienteMapper {

    private final ModelMapper modelMapper;

    public Cliente convertToEntiTy(ClienteRequestDTO clienteRequestDTO){
        return modelMapper.map(clienteRequestDTO, Cliente.class);
    }

    public ClienteResponseDTO convertToDTO(Cliente cliente){
        return modelMapper.map(cliente, ClienteResponseDTO.class);
    }

    public List<ClienteResponseDTO> convertToListDTO(List<Cliente> clientes){
        return clientes.stream().map(this::convertToDTO).toList();
    }

}

