package com.application.rest.RestTecnoPc.persistence.impl;

import com.application.rest.RestTecnoPc.entities.Category;
import com.application.rest.RestTecnoPc.persistence.ICategoryDAO;
import com.application.rest.RestTecnoPc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class CategoryDAOImpl implements ICategoryDAO {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAll() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {

        categoryRepository.save(category);

    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
