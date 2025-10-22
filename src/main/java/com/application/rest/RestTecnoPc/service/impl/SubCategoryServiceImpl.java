package com.application.rest.RestTecnoPc.service.impl;

import com.application.rest.RestTecnoPc.entities.Category;
import com.application.rest.RestTecnoPc.entities.Subcategory;
import com.application.rest.RestTecnoPc.persistence.ICategoryDAO;
import com.application.rest.RestTecnoPc.persistence.ISubCategoryDAO;
import com.application.rest.RestTecnoPc.service.ICategoryService;
import com.application.rest.RestTecnoPc.service.ISubCategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SubCategoryServiceImpl  implements ISubCategoryService {
    @Autowired
    private ISubCategoryDAO subCategoryDAO;


    @Override
    public Optional<Subcategory> findById(Long id) {
        return subCategoryDAO.findById(id);
    }

    @Override
    public List<Subcategory> findAll() {
        return subCategoryDAO.findAll();
    }

    @Override
    public void save(Subcategory subcategory) {
        subCategoryDAO.save(subcategory);
    }

    @Override
    public void deleteById(Long id) {
        subCategoryDAO.deleteById(id);
    }
}
