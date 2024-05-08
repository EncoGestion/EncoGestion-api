package com.KelvinGarcia.EncoGestion.SERVICE;

import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.ClienteMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.REPOSITORY.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteResponseDTO crearCuenta(ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = clienteMapper.convertToEntiTy(clienteRequestDTO);
        clienteRepository.save(cliente);
        return clienteMapper.convertToDTO(cliente);
    }

    public ClienteResponseDTO actualizarDatos(String id, ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrada con el id: "+id));

        cliente.setId(clienteRequestDTO.getId());
        cliente.setNombre(clienteRequestDTO.getNombre());
        cliente.setContrasenia(clienteRequestDTO.getContrasenia());
        cliente.setCorreo(clienteRequestDTO.getCorreo());
        cliente.setTelefono(clienteRequestDTO.getTelefono());

        cliente = clienteRepository.save(cliente);
        return clienteMapper.convertToDTO(cliente);
    }
}
