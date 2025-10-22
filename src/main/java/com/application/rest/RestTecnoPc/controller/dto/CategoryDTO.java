package com.application.rest.RestTecnoPc.controller.dto;

import java.util.ArrayList;
import java.util.List;

import com.application.rest.RestTecnoPc.entities.Subcategory;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    
    private Long id;
    private String nombre;
    private String descripcion;
   // private List<Subcategory> subcategories = new ArrayList<>();
}
