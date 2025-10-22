package com.application.rest.RestTecnoPc.controller.dto;

import java.math.BigDecimal;


import lombok.*;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 

public class ProductDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private int stock;
    private String imagenUrl;
    private BigDecimal price;
    private long subcategoryId;  
}
