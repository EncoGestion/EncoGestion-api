package com.KelvinGarcia.EncoGestion.MODEL.ENTITY;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name= "sobres")
public class Sobre{
    @Id
    @Column(name="id_sobre", nullable = false, unique = true)
    private Long id;
    @Column(name="peso",nullable = false)
    private float peso;
    @ManyToOne
    @JoinColumn(name="id_encomienda", nullable = false)
    private Encomienda encomienda;
}
