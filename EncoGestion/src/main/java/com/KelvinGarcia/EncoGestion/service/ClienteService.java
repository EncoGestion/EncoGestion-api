package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.ContraseñaEnUsoException;
import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.ClienteMapper;
import com.KelvinGarcia.EncoGestion.model.dto.ClienteRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.ClienteResponseCompletoDTO;
import com.KelvinGarcia.EncoGestion.model.dto.ClienteResponseDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SesionDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Cliente;
import com.KelvinGarcia.EncoGestion.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.KelvinGarcia.EncoGestion.exception.BadRequestException;

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
    public ClienteResponseCompletoDTO cambiarContraseña(String id, String contraseña){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrado con el id: "+id));
        if(cliente.getContrasenia().equals(contraseña)){
            throw new ContraseñaEnUsoException("No se puede asignar esta contraseña porque ya esta en uso");
        }
        else{
            cliente.setContrasenia(contraseña);
        }
        clienteRepository.save(cliente);
        return clienteMapper.convertToCompletoDTO(cliente);
    }

    @Transactional
    public boolean iniciarSesionCliente(SesionDTO clienteSesionDTO){
        boolean sesion = true;
        Cliente cliente = clienteRepository.iniciarSesionCliente(clienteSesionDTO.getCorreo());

        if (!cliente.getContrasenia().equals(clienteSesionDTO.getContraseña())) {
            throw new ResourceNotFoundException("El correo o la contraseña son incorrectas");
        }

        if(cliente.getCorreo().isEmpty()){
            throw new BadRequestException("El correo no puede ser vacío");
        }

        return sesion;
    }

    @Transactional
    public void eliminarCliente(String id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
