package com.KelvinGarcia.EncoGestion.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name= "repartidores")
public class Repartidor {

    @Id
    @Column(name="id_repartidor",nullable = false, unique=true, columnDefinition = "VARCHAR(8)")
    private String id;
    @Column(name="nombre",nullable = false)
    private String nombre;
    @Column(name="contrasenia",nullable = false)
    private String contrasenia;
    @Column(name="telefono",nullable = false, columnDefinition = "VARCHAR(9)")
    private String telefono;
    @Column(name="correo",nullable = false, unique=true)
    private String correo;
    @Column(name="estado",nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'Libre'")
    private String estado;
    @Column(name="ubi_provincia",nullable = false)
    private String ubiProvincia;


}
