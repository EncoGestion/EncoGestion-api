package com.KelvinGarcia.EncoGestion.SERVICE;

import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.EncomiendaMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.EncomiendaResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Encomienda;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.Cliente;
import com.KelvinGarcia.EncoGestion.REPOSITORY.ClienteRepository;
import com.KelvinGarcia.EncoGestion.REPOSITORY.EncomiendaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EncomiendaService {

    private final EncomiendaRepository encomiendaRepository;
    private final ClienteRepository clienteRepository;
    private final EncomiendaMapper encomiendaMapper;

    @Transactional(readOnly = true)
    public List<EncomiendaResponseDTO> getAllEncomiendas() {
        List<Encomienda> encomiendas = encomiendaRepository.findAll();
        return encomiendaMapper.convertToListDTO(encomiendas);
    }

    @Transactional(readOnly = true)
    public List<EncomiendaResponseDTO> getEncomiendasByClienteId(String id) {
        List<Encomienda> encomiendas = encomiendaRepository.getEncomiendaFromCliente(id);
        if(encomiendas.isEmpty()){
            throw new ResourceNotFoundException("El cliente mo tiene encomiendas o no existe");
        }
        return encomiendaMapper.convertToListDTO(encomiendas);
    }

    @Transactional(readOnly = true)
    public List<EncomiendaResponseDTO> getEncomiendasByRepartidorId(String id) {
        List<Encomienda> encomiendas = encomiendaRepository.getEncomiendaFromRepartidor(id);
        if(encomiendas.isEmpty()){
            throw new ResourceNotFoundException("El repartidor mo tiene encomiendas o no existe");
        }
        return encomiendaMapper.convertToListDTO(encomiendas);
    }

    @Transactional(readOnly = true)
    public List<EncomiendaResponseDTO> buscarEncomiendaDeClientePorFecha(LocalDate fecha, String clienteID) {

        Cliente cliente = clienteRepository.findById(clienteID)
                .orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrada con el id: "+clienteID));

        List<Encomienda> encomiendas = encomiendaRepository.getEncomiendaByDateAndClienteID(fecha, cliente);
        if (encomiendas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron encomiendas para el cliente con DNI " + clienteID + " en la fecha " + fecha);
        }
        return encomiendaMapper.convertToListDTO(encomiendas);
    }

}
