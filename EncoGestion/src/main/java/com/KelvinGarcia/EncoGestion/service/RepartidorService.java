package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.ContraseñaEnUsoException;
import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.RepartidorMapper;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorResponseCompletoDTO;
import com.KelvinGarcia.EncoGestion.model.dto.RepartidorResponseDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SesionDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Repartidor;
import com.KelvinGarcia.EncoGestion.repository.RepartidorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RepartidorService {

    private final RepartidorRepository repartidorRepository;
    private final RepartidorMapper repartidorMapper;

    @Transactional(readOnly = true)
    public RepartidorResponseDTO obtenerRepartidorPorID(String id){
        Repartidor repartidor = repartidorRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrada con el numero: "+id));
        return repartidorMapper.convertToDTO(repartidor);
    }

    @Transactional
    public RepartidorResponseDTO crearRepartidor(RepartidorRequestDTO repartidorRequestDTO){
        Repartidor repartidor = repartidorMapper.convertToEntiTy(repartidorRequestDTO);
        repartidorRepository.save(repartidor);
        return repartidorMapper.convertToDTO(repartidor);
    }
    @Transactional(readOnly = true)
    public boolean inicioSesionRepartidor(SesionDTO sesionDTO) {
        Repartidor repartidor = repartidorRepository.inicioSesionRepartidor(sesionDTO.getCorreo());

        if (repartidor != null && repartidor.getContrasenia().equals(sesionDTO.getContraseña())) {
            return true;
        } else {
            throw new ResourceNotFoundException("El correo o la contraseña son incorrectos");
        }
    }

    @Transactional
    public RepartidorResponseCompletoDTO cambiarContraseña(String id, String contraseña){
        Repartidor repartidor = repartidorRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrado con el id: "+id));
        if(repartidor.getContrasenia().equals(contraseña)){
            throw new ContraseñaEnUsoException("No se puede asignar esta contraseña porque ya esta en uso");
        }
        else{
            repartidor.setContrasenia(contraseña);
        }
        repartidorRepository.save(repartidor);
        return repartidorMapper.convertToCompletoDTO(repartidor);
    }

    @Transactional
    public void eliminar(String id) {
        if (!repartidorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Repartidor no encontrado con ID: " + id);
        }
        repartidorRepository.deleteById(id);
    }

    @Transactional
    public Repartidor editarPerfil(String id, Repartidor repartidorActualizado) {
        Repartidor repartidor = repartidorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Repartidor no encontrado"));

        repartidor.setContrasenia(repartidorActualizado.getContrasenia());
        repartidor.setTelefono(repartidorActualizado.getTelefono());
        repartidor.setCorreo(repartidorActualizado.getCorreo());
        repartidor.setEstado(repartidorActualizado.getEstado());
        repartidor.setUbiProvincia(repartidor.getUbiProvincia());

        return repartidorRepository.save(repartidor);
    }

}
