package com.KelvinGarcia.EncoGestion.mapper;

import com.KelvinGarcia.EncoGestion.model.dto.ClienteRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.ClienteResponseCompletoDTO;
import com.KelvinGarcia.EncoGestion.model.dto.ClienteResponseDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Cliente;
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

    public ClienteResponseCompletoDTO convertToCompletoDTO(Cliente cliente){
        return modelMapper.map(cliente, ClienteResponseCompletoDTO.class);
    }
}

