package com.KelvinGarcia.EncoGestion.SERVICE;

import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.RepartidorMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorRequestDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.RepartidorSesionDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Repartidor;
import com.KelvinGarcia.EncoGestion.REPOSITORY.RepartidorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RepartidorService {

    private final RepartidorRepository repartidorRepository;
    private final RepartidorMapper repartidorMapper;

    @Transactional(readOnly = true)
    public List<RepartidorResponseDTO> getAllRepartidores(){
        List<Repartidor> repartidores = repartidorRepository.findAll();
        return repartidorMapper.convertToListDTO(repartidores);
    }

    @Transactional(readOnly = true)
    public RepartidorResponseDTO getRepartidorByID(Long id){
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
    public boolean inicioSesionRepartidor(RepartidorSesionDTO repartidorSesionDTO) {
        Repartidor repartidor = repartidorRepository.inicioSesionRepartidor(repartidorSesionDTO.getCorreo());

        if (repartidor != null && repartidor.getContrasenia().equals(repartidorSesionDTO.getContraseña())) {
            return true;
        } else {
            throw new ResourceNotFoundException("El correo o la contraseña son incorrectos");
        }
    }
}
