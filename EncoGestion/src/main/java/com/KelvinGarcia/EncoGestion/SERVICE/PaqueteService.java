package com.KelvinGarcia.EncoGestion.SERVICE;

import com.KelvinGarcia.EncoGestion.MAPPER.PaqueteMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.PaqueteResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Paquete;
import com.KelvinGarcia.EncoGestion.REPOSITORY.PaqueteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;
    private final PaqueteMapper paqueteMapper;

    @Transactional
    public List<PaqueteResponseDTO> devolverPaquetes(Encomienda encomienda){
        List<Paquete> paquetes = paqueteRepository.listaDePaquete(encomienda);
        return paqueteMapper.convertToListDTO(paquetes);
    }
}
