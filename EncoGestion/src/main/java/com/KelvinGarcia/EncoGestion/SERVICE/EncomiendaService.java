package com.KelvinGarcia.EncoGestion.SERVICE;

import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.EncomiendaMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.*;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.*;
import com.KelvinGarcia.EncoGestion.REPOSITORY.ClienteRepository;
import com.KelvinGarcia.EncoGestion.REPOSITORY.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.REPOSITORY.RepartidorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EncomiendaService {

    private final EncomiendaRepository encomiendaRepository;
    private final ClienteRepository clienteRepository;
    private final RepartidorRepository repartidorRepository;
    private final EncomiendaMapper encomiendaMapper;
    private final PaqueteService paqueteService;
    private final SobreService sobreService;

    @Transactional(readOnly = true)
    public List<EncomiendaHistorialDTO> getEncomiendasByClienteId(String id) {
        List<Encomienda> encomiendas = encomiendaRepository.getEncomiendaFromCliente(id);
        if(encomiendas.isEmpty()){
            throw new ResourceNotFoundException("No hay encomiendas para el DNI:  "+id+" o no existe el usuario");
        }
        List<EncomiendaHistorialDTO> encomiendaHistorialDTOs = new ArrayList<>();

        for (Encomienda encomienda : encomiendas) {
            List<PaqueteResponseDTO> paquetes = paqueteService.devolverPaquetes(encomienda);
            List<SobreResponseDTO> sobres = sobreService.devolverSobres(encomienda);
            EncomiendaHistorialDTO historialDTO = encomiendaMapper.convertToHistorialDTO(encomienda, paquetes, sobres);
            encomiendaHistorialDTOs.add(historialDTO);
        }
        return encomiendaHistorialDTOs;
    }

    @Transactional(readOnly = true)
    public List<EncomiendaHistorialDTO> getEncomiendasByRepartidorId(String id) {
        List<Encomienda> encomiendas = encomiendaRepository.getEncomiendaFromRepartidor(id);
        if(encomiendas.isEmpty()){
            throw new ResourceNotFoundException("El repartidor mo tiene encomiendas o no existe");
        }
        List<EncomiendaHistorialDTO> encomiendaHistorialDTOs = new ArrayList<>();

        for (Encomienda encomienda : encomiendas) {
            List<PaqueteResponseDTO> paquetes = paqueteService.devolverPaquetes(encomienda);
            List<SobreResponseDTO> sobres = sobreService.devolverSobres(encomienda);
            EncomiendaHistorialDTO historialDTO = encomiendaMapper.convertToHistorialDTO(encomienda, paquetes, sobres);
            encomiendaHistorialDTOs.add(historialDTO);
        }
        return encomiendaHistorialDTOs;
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

    public Encomienda actualizarEstado(Long id, String nuevoEstado) {
        Encomienda encomienda = encomiendaRepository.findById(id).orElseThrow(() -> new RuntimeException("Encomienda no encontrada"));
        encomienda.setEstado(nuevoEstado);
        encomienda.setFecha(LocalDate.now());
        encomienda.setHora(LocalTime.now());
        return encomiendaRepository.save(encomienda);
    }

    @Transactional
    public List<EncomiendaResponseDTO> asignarEncomienda(String proOrigen, String estado, String id_empleado) {
        List<Encomienda> encomiendas = encomiendaRepository.buscarEncomiendasParaAsignar(proOrigen, estado);
        Repartidor repartidor = repartidorRepository.findById(id_empleado)
                .orElseThrow(()-> new ResourceNotFoundException("Repartidor no encontrada con el id: "+id_empleado));

        if(encomiendas.isEmpty()){
            throw new ResourceNotFoundException("No se encontraron encomiendas para la provincia "+proOrigen);
        }
        else{
            if(!repartidor.getNombre().isEmpty()){
                for (Encomienda encomienda : encomiendas) {
                    encomiendaRepository.updateRepartidor(encomienda.getId(), repartidor);
                }
            }

        }
        return encomiendaMapper.convertToListDTO(encomiendas);
    }

    @Transactional
    public EncomiendaResponseDTO crearEncomienda(EncomiendaRequestDTO encomiendaRequestDTO, String idCliente) {
        Long id;
        Encomienda encomiendaPrueba;
        Encomienda encomienda = new Encomienda();
        Cliente clienteRemitente = clienteRepository.buscarPorId(idCliente);

        if(clienteRemitente==null){
            throw new ResourceNotFoundException("Cliente no encontrado con el DNI: "+idCliente);
        }
        else{
            do{
                id = Math.round(Math.random()*1000000);
                encomiendaPrueba = encomiendaRepository.findBySourceOrEncomiendaID(id);
            }while(encomiendaPrueba!=null);

            encomiendaRequestDTO.setId(id);
            encomiendaRequestDTO.setClienteRemitente(clienteRemitente);
            encomienda = encomiendaMapper.convertToEntity(encomiendaRequestDTO);
            encomiendaRepository.save(encomienda);
        }

        return encomiendaMapper.convertToDTO(encomienda);
    }

}
