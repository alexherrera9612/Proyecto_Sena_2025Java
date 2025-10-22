package com.application.rest.RestTecnoPc.persistence.impl;

import com.application.rest.RestTecnoPc.entities.Subcategory;
import com.application.rest.RestTecnoPc.persistence.ISubCategoryDAO;
import com.application.rest.RestTecnoPc.repository.SubCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class SubCategoryDAOImpl implements ISubCategoryDAO {

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Override
    public Optional<Subcategory> findById(Long id) {
        return subCategoryRepository.findById(id);
    }

    @Override
    public List<Subcategory> findAll() {
        return (List<Subcategory>) subCategoryRepository.findAll();
    }

    @Override
    public void save(Subcategory subcategory) {

        subCategoryRepository.save(subcategory);

    }

    @Override
    public void deleteById(Long id) {
        subCategoryRepository.deleteById(id);
    }
}
