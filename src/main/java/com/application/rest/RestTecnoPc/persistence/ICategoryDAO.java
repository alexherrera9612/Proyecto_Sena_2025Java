package com.application.rest.RestTecnoPc.persistence;

import com.application.rest.RestTecnoPc.entities.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryDAO {
    Optional<Category> findById(Long id);
    List<Category> findAll();

    void save (Category category);
    void deleteById(Long id);
}
