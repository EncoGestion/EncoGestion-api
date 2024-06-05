package com.KelvinGarcia.EncoGestion.MODEL.ENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.core.annotation.Order;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name= "clientes")
public class Cliente {

    @Id
    @Column(name="id_cliente", nullable=false, unique=true, columnDefinition = "VARCHAR(8)")
    private String id;
    @Column(name="nombre", nullable=false)
    private String nombre;
    @Column(name="contrasenia", nullable=false)
    private String contrasenia;
    @Column(name="correo", nullable=false, unique=true)
    private String correo;
    @Column(name="telefono", nullable=false, columnDefinition = "VARCHAR(9)")
    private String telefono;

}
