package com.KelvinGarcia.EncoGestion.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name= "paquetes")
public class Paquete{

    @Id
    @Column(name="id_paquete", nullable = false, unique=true)
    private Long id;
    @Column(name="ancho",nullable = false)
    private float ancho;
    @Column(name="alto",nullable = false)
    private float alto;
    @Column(name="largo",nullable = false)
    private float largo;
    @Column(name="peso",nullable = false)
    private float peso;
    @ManyToOne
    @JoinColumn(name="id_encomienda", nullable = false)
    private Encomienda encomienda;

}