package com.application.rest.RestTecnoPc.service;

import com.application.rest.RestTecnoPc.entities.Subcategory;

import java.util.List;
import java.util.Optional;

public interface ISubCategoryService {
    Optional<Subcategory> findById(Long id);
    List<Subcategory> findAll();

    void save (Subcategory subcategory);
    void deleteById(Long id);
}
