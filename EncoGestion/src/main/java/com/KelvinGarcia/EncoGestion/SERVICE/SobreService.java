package com.KelvinGarcia.EncoGestion.SERVICE;

import com.KelvinGarcia.EncoGestion.MAPPER.SobreMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.SobreResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Sobre;
import com.KelvinGarcia.EncoGestion.REPOSITORY.SobreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class SobreService {

    private final SobreRepository sobreRepository;
    private final SobreMapper sobreMapper;

    @Transactional(readOnly = true)
    public List<SobreResponseDTO> devolverSobres(Encomienda encomienda) {
        List<Sobre> sobres = sobreRepository.listaDeSobre(encomienda);
        return sobreMapper.convertToListDTO(sobres);
    }
}
