package com.KelvinGarcia.EncoGestion.SERVICE;


import com.KelvinGarcia.EncoGestion.MODEL.DTO.CotizacionResponseDTO;
import com.KelvinGarcia.EncoGestion.MODEL.DTO.EncomiendaRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class EncomiendaService {

    public CotizacionResponseDTO cotizarEncomienda(EncomiendaRequestDTO encomiendaRequestDTO) {
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