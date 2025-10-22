package com.application.rest.RestTecnoPc.persistence;

import com.application.rest.RestTecnoPc.entities.Subcategory;
import java.util.List;
import java.util.Optional;

public interface ISubCategoryDAO {
    Optional<Subcategory> findById(Long id);
    List<Subcategory> findAll();
    void save (Subcategory subcategory);
    void deleteById(Long id);
}
