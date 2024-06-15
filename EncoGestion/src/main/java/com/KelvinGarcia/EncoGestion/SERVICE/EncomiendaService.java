package com.KelvinGarcia.EncoGestion.SERVICE;

import com.KelvinGarcia.EncoGestion.EXCEPTION.EstadoYaAsignadoException;
import com.KelvinGarcia.EncoGestion.EXCEPTION.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.MAPPER.EncomiendaMapper;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.*;
import com.KelvinGarcia.EncoGestion.MODEL.ENTITY.*;
import com.KelvinGarcia.EncoGestion.REPOSITORY.ClienteRepository;
import com.KelvinGarcia.EncoGestion.REPOSITORY.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.REPOSITORY.RepartidorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<EncomiendaHistorialDTO> getEncomiendasByClienteId(String id) {
        List<Encomienda> encomiendas = encomiendaRepository.getEncomiendaFromCliente(id);
        List<EncomiendaHistorialDTO> encomiendaHistorialDTOs = new ArrayList<>();
        if(encomiendas.isEmpty()){
            throw new ResourceNotFoundException("No hay encomiendas para el DNI:  "+id+" o no existe el usuario");
        }
        else{
            for (Encomienda encomienda : encomiendas) {
                List<PaqueteResponseDTO> paquetes = paqueteService.devolverPaquetes(encomienda);
                List<SobreResponseDTO> sobres = sobreService.devolverSobres(encomienda);
                EncomiendaHistorialDTO historialDTO = encomiendaMapper.convertToHistorialDTO(encomienda, paquetes, sobres);
                encomiendaHistorialDTOs.add(historialDTO);
            }
        }
        return encomiendaHistorialDTOs;
    }

    @Transactional(readOnly = true)
    public List<EncomiendaHistorialDTO> getEncomiendasByRepartidorId(String id) {
        List<Encomienda> encomiendas = encomiendaRepository.getEncomiendaFromRepartidor(id);
        List<EncomiendaHistorialDTO> encomiendaHistorialDTOs = new ArrayList<>();
        if(encomiendas.isEmpty()){
            throw new ResourceNotFoundException("El repartidor mo tiene encomiendas o no existe");
        }
        else{
            for (Encomienda encomienda : encomiendas) {
                List<PaqueteResponseDTO> paquetes = paqueteService.devolverPaquetes(encomienda);
                List<SobreResponseDTO> sobres = sobreService.devolverSobres(encomienda);
                EncomiendaHistorialDTO historialDTO = encomiendaMapper.convertToHistorialDTO(encomienda, paquetes, sobres);
                encomiendaHistorialDTOs.add(historialDTO);
            }
        }
        return encomiendaHistorialDTOs;
    }


    @Transactional(readOnly = true)
    public List<EncomiendaHistorialDTO> buscarEncomiendaDeClientePorFecha(LocalDate fecha, String clienteID) {

        Cliente cliente = clienteRepository.findById(clienteID)
                .orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrada con el id: "+clienteID));

        List<Encomienda> encomiendas = encomiendaRepository.getEncomiendaByDateAndClienteID(fecha, cliente);

        List<EncomiendaHistorialDTO> encomiendaHistorialDTOs = new ArrayList<>();

        if (encomiendas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron encomiendas para el cliente con DNI " + clienteID + " en la fecha " + fecha);
        }
        else{
            for (Encomienda encomienda : encomiendas) {
                List<PaqueteResponseDTO> paquetes = paqueteService.devolverPaquetes(encomienda);
                List<SobreResponseDTO> sobres = sobreService.devolverSobres(encomienda);
                EncomiendaHistorialDTO historialDTO = encomiendaMapper.convertToHistorialDTO(encomienda, paquetes, sobres);
                encomiendaHistorialDTOs.add(historialDTO);
            }
        }
        return encomiendaHistorialDTOs;
    }

    @Transactional(readOnly = true)
    public List<EncomiendaHistorialDTO> buscarEncomiendaDeRepartidorPorFecha(LocalDate fecha, String repartidorID) {

        Repartidor repartidor = repartidorRepository.findById(repartidorID)
                .orElseThrow(()-> new ResourceNotFoundException("Cuenta no encontrada con el id: "+repartidorID));

        List<Encomienda> encomiendas = encomiendaRepository.getEncomiendaByDateAndRepartidorID(fecha,repartidor);

        List<EncomiendaHistorialDTO> encomiendaHistorialDTOs = new ArrayList<>();

        if (encomiendas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron encomiendas para el repartidor con DNI " + repartidorID + " en la fecha " + fecha);
        }
        else{
            for (Encomienda encomienda : encomiendas) {
                List<PaqueteResponseDTO> paquetes = paqueteService.devolverPaquetes(encomienda);
                List<SobreResponseDTO> sobres = sobreService.devolverSobres(encomienda);
                EncomiendaHistorialDTO historialDTO = encomiendaMapper.convertToHistorialDTO(encomienda, paquetes, sobres);
                encomiendaHistorialDTOs.add(historialDTO);
            }
        }
        return encomiendaHistorialDTOs;
    }

    @Transactional
    public EncomiendaGmailDTO actualizarEstado(Long id, String nuevoEstado) {
        Encomienda encomienda = encomiendaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Encomienda no encontrada"));
        String contenidoCorreo, correo;

        if(nuevoEstado.equals(encomienda.getEstado())){
            throw new EstadoYaAsignadoException("Este estado ya ha sido asignado");
        }
        else{
            if(nuevoEstado.equals("En camino")){
                encomienda.setEstado(nuevoEstado);
            }
            if(nuevoEstado.equals("En destino")||nuevoEstado.equals("Entregado")){
                encomienda.setEstado(nuevoEstado);
                encomienda.setFecha(LocalDate.now());
                encomienda.setHora(LocalTime.now());
            }
            encomiendaRepository.save(encomienda);
            contenidoCorreo = "Su encomienda con numero de tracking: "+encomienda.getId()
                    +" con origen: "+encomienda.getDepOrigen()+", "+encomienda.getProOrigen()+", "+encomienda.getDisOrigen()
                    +" y destino: "+encomienda.getDepDestino()+", "+encomienda.getProDestino()+", "+encomienda.getDisDestino()
                    +" estÃ¡ "+encomienda.getEstado();

            correo = encomienda.getClienteRemitente().getCorreo();

        }
        return encomiendaMapper.convertToGmailDTO(contenidoCorreo, correo);
    }

    @Transactional
    public List<EncomiendaHistorialDTO> asignarEncomienda(String proOrigen, String estado, String id_repartidor) {
        Repartidor repartidor = repartidorRepository.findById(id_repartidor)
                .orElseThrow(()-> new ResourceNotFoundException("Repartidor no encontrada con el id: "+id_repartidor));
        List<Encomienda> encomiendas = encomiendaRepository.buscarEncomiendasParaAsignar(proOrigen, estado);

        List<EncomiendaHistorialDTO> encomiendaHistorialDTOs = new ArrayList<>();

        if(encomiendas.isEmpty()){
            throw new ResourceNotFoundException("No se encontraron encomiendas para la provincia "+proOrigen);
        }
        else{
            if(!repartidor.getNombre().isEmpty()){
                for (Encomienda encomienda : encomiendas) {
                    encomiendaRepository.updateRepartidor(encomienda.getId(), repartidor);
                    entityManager.refresh(encomienda);
                    List<PaqueteResponseDTO> paquetes = paqueteService.devolverPaquetes(encomienda);
                    List<SobreResponseDTO> sobres = sobreService.devolverSobres(encomienda);
                    Encomienda encomiendaActualizada = encomiendaRepository.findBySourceOrEncomiendaID(encomienda.getId());
                    EncomiendaHistorialDTO historialDTO = encomiendaMapper.convertToHistorialDTO(encomiendaActualizada, paquetes, sobres);
                    encomiendaHistorialDTOs.add(historialDTO);
                }
            }
        }
        return encomiendaHistorialDTOs;
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
