package com.KelvinGarcia.EncoGestion.MODEL.ENTITY;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.core.annotation.Order;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name= "encomiendas")
public class Encomienda {

    @Id
    @Column(name="id_encomienda", nullable=false, unique=true)
    private Long id;
    @Column(name="dep_origen", nullable=false)
    private String depOrigen;
    @Column(name="pro_origen", nullable=false)
    private String proOrigen;
    @Column(name="dis_origen", nullable=false)
    private String disOrigen;
    @Column(name="dep_destino", nullable=false)
    private String depDestino;
    @Column(name="pro_destino", nullable=false)
    private String proDestino;
    @Column(name="dis_destino", nullable=false)
    private String disDestino;
    @Column(name="direccion")
    private String direccion;
    @Column(name="fecha")
    private LocalDate fecha;
    @Column(name="hora")
    private LocalTime hora;
    @Column(name="estado", columnDefinition = "VARCHAR(255) DEFAULT 'En espera'" )
    private String estado;
    @ManyToOne
    @JoinColumn(name="id_cliente", nullable=false)
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name="id_repartidor")
    private Repartidor repartidor;

}
