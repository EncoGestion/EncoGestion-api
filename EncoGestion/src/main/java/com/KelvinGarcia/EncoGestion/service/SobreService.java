package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.SobreMapper;
import com.KelvinGarcia.EncoGestion.model.dto.SobreRequestDTO;
import com.KelvinGarcia.EncoGestion.model.dto.SobreResponseDTO;
import com.KelvinGarcia.EncoGestion.model.entity.Encomienda;
import com.KelvinGarcia.EncoGestion.model.entity.Sobre;
import com.KelvinGarcia.EncoGestion.repository.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.repository.SobreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SobreService {

    private final SobreRepository sobreRepository;
    private final EncomiendaRepository encomiendaRepository;
    private final SobreMapper sobreMapper;

    @Transactional(readOnly = true)
    public List<SobreResponseDTO> devolverSobres(Encomienda encomienda) {
        List<Sobre> sobres = sobreRepository.listaDeSobre(encomienda);
        return sobreMapper.convertToListDTO(sobres);
    }

    @Transactional
    public SobreResponseDTO registrarSobre(SobreRequestDTO sobreRequestDTO, Long encomiendaId) {
        Long id;
        Sobre sobrePrueba;
        Sobre sobre = new Sobre();
        Encomienda encomienda = encomiendaRepository.buscarEncomiendaID(encomiendaId);

        if (encomienda == null) {
            throw new ResourceNotFoundException("La encomienda no existe");
        }
        else{
            do{
                id = Math.round(Math.random()*1000000);
                sobrePrueba = sobreRepository.buscarSobreId(id);
            }while(sobrePrueba!=null);

            sobreRequestDTO.setId(id);
            sobreRequestDTO.setEncomienda(encomienda);
            sobre = sobreMapper.convertToEntity(sobreRequestDTO);
            sobreRepository.save(sobre);
        }
        return sobreMapper.convertToDTO(sobre);
    }
}
