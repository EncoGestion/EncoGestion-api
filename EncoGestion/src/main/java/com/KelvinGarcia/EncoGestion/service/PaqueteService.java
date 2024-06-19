package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.PaqueteMapper;
import com.KelvinGarcia.EncoGestion.model.dto.PaqueteRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.PaqueteResponseDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Encomienda;
import com.KelvinGarcia.EncoGestion.model.entity.Paquete;
import com.KelvinGarcia.EncoGestion.repository.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.repository.PaqueteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PaqueteService {

    private final PaqueteRepository paqueteRepository;
    private final EncomiendaRepository encomiendaRepository;
    private final PaqueteMapper paqueteMapper;

    @Transactional
    public List<PaqueteResponseDTO> devolverPaquetes(Encomienda encomienda){
        List<Paquete> paquetes = paqueteRepository.listaDePaquete(encomienda);
        return paqueteMapper.convertToListDTO(paquetes);
    }

    @Transactional
    public PaqueteResponseDTO registrarPaquete(PaqueteRequestDTO paqueteRequestDTO, Long encomiendaiD){
        Long id;
        Paquete paquetePrueba;
        Paquete paquete = new Paquete();
        Encomienda encomienda = encomiendaRepository.buscarEncomiendaID(encomiendaiD);

        if(encomienda == null){
            throw new ResourceNotFoundException("La encomienda no existe");
        }
        else{
            do{
                id = Math.round(Math.random()*1000000);
                paquetePrueba = paqueteRepository.buscarPaqueteId(id);
            }while(paquetePrueba != null);

            paqueteRequestDTO.setId(id);
            paqueteRequestDTO.setEncomienda(encomienda);
            paquete = paqueteMapper.convertToEntity(paqueteRequestDTO);
            paqueteRepository.save(paquete);
        }
        return paqueteMapper.convertToDTO(paquete);
    }

}
