package com.KelvinGarcia.EncoGestion.service;

import com.KelvinGarcia.EncoGestion.exception.EstadoYaAsignadoException;
import com.KelvinGarcia.EncoGestion.exception.ResourceNotFoundException;
import com.KelvinGarcia.EncoGestion.mapper.EncomiendaMapper;
import com.KelvinGarcia.EncoGestion.model.dto.*;
import com.KelvinGarcia.EncoGestion.model.entity.*;
import com.KelvinGarcia.EncoGestion.repository.ClienteRepository;
import com.KelvinGarcia.EncoGestion.repository.EncomiendaRepository;
import com.KelvinGarcia.EncoGestion.repository.RepartidorRepository;
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
    public List<EncomiendaHistorialDTO> obtenerEncomiendasDelClienteId(String id) {
        List<Encomienda> encomiendas = encomiendaRepository.obtenerEncomiendasDelCliente(id);
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
    public List<EncomiendaHistorialDTO> obtenerEncomiendasDelRepartidorId(String id) {
        List<Encomienda> encomiendas = encomiendaRepository.obtenerEncomiendasDelRepartidor(id);
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

        List<Encomienda> encomiendas = encomiendaRepository.obtenerEncomiendaPorFecha_ClienteID(fecha, cliente);

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

        List<Encomienda> encomiendas = encomiendaRepository.obtenerEncomiendaPorFecha_RepartidorID(fecha,repartidor);

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
    public EncomiendaGmailDTO notificarAutomaticamente(Long id, String nuevoEstado) {
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
    public List<EncomiendaHistorialDTO> asignarEncomienda(String proOrigen, String id_repartidor) {
        Repartidor repartidor = repartidorRepository.findById(id_repartidor)
                .orElseThrow(()-> new ResourceNotFoundException("Repartidor no encontrada con el id: "+id_repartidor));
        List<Encomienda> encomiendas = encomiendaRepository.buscarEncomiendasParaAsignar(proOrigen);

        List<EncomiendaHistorialDTO> encomiendaHistorialDTOs = new ArrayList<>();

        if(encomiendas.isEmpty()){
            throw new ResourceNotFoundException("No se encontraron encomiendas para la provincia "+proOrigen);
        }
        else{
            if(!repartidor.getNombre().isEmpty()){
                for (Encomienda encomienda : encomiendas) {
                    encomiendaRepository.actualizarRepartidor(encomienda.getId(), repartidor);
                    entityManager.refresh(encomienda);
                    List<PaqueteResponseDTO> paquetes = paqueteService.devolverPaquetes(encomienda);
                    List<SobreResponseDTO> sobres = sobreService.devolverSobres(encomienda);
                    Encomienda encomiendaActualizada = encomiendaRepository.buscarEncomiendaID(encomienda.getId());
                    EncomiendaHistorialDTO historialDTO = encomiendaMapper.convertToHistorialDTO(encomiendaActualizada, paquetes, sobres);
                    encomiendaHistorialDTOs.add(historialDTO);
                }
            }
        }
        return encomiendaHistorialDTOs;
    }

    @Transactional
    public EncomiendaResponseDTO registrarEncomienda(EncomiendaRequestDTO encomiendaRequestDTO, String idCliente) {
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
                encomiendaPrueba = encomiendaRepository.buscarEncomiendaID(id);
            }while(encomiendaPrueba!=null);

            encomiendaRequestDTO.setId(id);
            encomiendaRequestDTO.setEstado("Por enviar");
            encomiendaRequestDTO.setClienteRemitente(clienteRemitente);
            encomienda = encomiendaMapper.convertToEntity(encomiendaRequestDTO);
            encomiendaRepository.save(encomienda);
        }

        return encomiendaMapper.convertToDTO(encomienda);
    }

    public String consultarEstado(Long id) {
        Encomienda encomienda = encomiendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Encomienda no encontrada con N° de Tracking: " + id));
        return encomienda.getEstado();
    }

    public CotizacionResponseDTO cotizarEncomienda(CotizarEncomiendaRequestDTO encomiendaRequestDTO) {
        double peso = encomiendaRequestDTO.getPeso();
        double alto = encomiendaRequestDTO.getAltura();
        double ancho = encomiendaRequestDTO.getAncho();
        double largo = encomiendaRequestDTO.getLargo();

        double costoEnvio = calcularCosto(peso, alto, ancho, largo);

        return new CotizacionResponseDTO(costoEnvio);
    }

    private double calcularCosto(double peso, double alto, double ancho, double largo) {
        double costoEnvio;
        if(alto==0 && ancho==0 && largo==0) {
            costoEnvio= peso * 5;
        }
        else{
            costoEnvio = peso * 1 + alto * 0.1 + ancho * 0.1 + largo * 0.1;
        }
        return costoEnvio;
    }

}