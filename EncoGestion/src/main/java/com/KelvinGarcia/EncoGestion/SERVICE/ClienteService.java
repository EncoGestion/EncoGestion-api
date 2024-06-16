package com.KelvinGarcia.EncoGestion.SERVICE;

import com.KelvinGarcia.EncoGestion.EXCEPTION.ContraseñaEnUsoException;
import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.ClienteMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.REPOSITORY.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.ClienteSesionDTO;
import com.KelvinGarcia.EncoGestion.EXCEPTION.BadRequestException;

@Service
@AllArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    @Transactional
    public ClienteResponseDTO crearCuenta(ClienteRequestDTO clienteRequestDTO){
        Cliente cliente = clienteMapper.convertToEntiTy(clienteRequestDTO);
        clienteRepository.save(cliente);
        return clienteMapper.convertToDTO(cliente);
    }

    @Transactional
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

    @Transactional
    public Cliente cambiarContraseña(String id, String contraseña){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrado con el id: "+id));
        if(cliente.getContrasenia().equals(contraseña)){
            throw new ContraseñaEnUsoException("No se puede asignar esta contraseña porque ya esta en uso");
        }
        else{
            cliente.setContrasenia(contraseña);
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public boolean iniciarSesionCliente(ClienteSesionDTO clienteSesionDTO){
        boolean sesion = true;
        Cliente cliente = clienteRepository.iniciarSesionCliente(clienteSesionDTO.getCorreo());

        if (!cliente.getContrasenia().equals(clienteSesionDTO.getContrasenia())) {
            throw new ResourceNotFoundException("El correo o la contraseña son incorrectas");
        }

        if(cliente.getCorreo().isEmpty()){
            throw new BadRequestException("El correo no puede ser vacío");
        }

        return sesion;
    }
}
