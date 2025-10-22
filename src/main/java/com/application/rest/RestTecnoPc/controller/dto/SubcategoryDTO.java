package com.application.rest.RestTecnoPc.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.application.rest.RestTecnoPc.entities.Product;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SubcategoryDTO {
    private Long id;
    private String nombre;
    private long categoryId; // Relación con categoría padre
   // private List<Product> productList = new ArrayList<>();
}