package com.egg.electricidad.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@Table(name = "t_articulo", schema = "electricidad")
@Data
@NoArgsConstructor
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nro_articulo", unique = true, nullable = false)
    private Integer nroArticulo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_fabrica", nullable = false)
    private Fabrica fabrica;

}
