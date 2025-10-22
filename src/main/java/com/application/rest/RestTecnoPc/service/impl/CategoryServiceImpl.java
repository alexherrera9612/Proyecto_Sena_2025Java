package com.application.rest.RestTecnoPc.service.impl;

import com.application.rest.RestTecnoPc.entities.Category;
import com.application.rest.RestTecnoPc.persistence.ICategoryDAO;
import com.application.rest.RestTecnoPc.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl  implements ICategoryService {
    @Autowired
    private ICategoryDAO categoryDAO;


    @Override
    public Optional<Category> findById(Long id) {
        return categoryDAO.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public void save(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public void deleteById(Long id) {
        categoryDAO.deleteById(id);
    }
}
