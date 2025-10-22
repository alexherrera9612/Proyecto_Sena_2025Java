package com.application.rest.RestTecnoPc.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nombre;
    @Column(name = "description")
    private String descripcion;
    @Column(name = "price")
    private BigDecimal price;
    private int stock;
    @Column(name = "imagen_url")
    private String imagenUrl;
    @ManyToOne
    @JoinColumn(name = "id_subcategoria",nullable = false)
    private Subcategory subcategoria;


}
