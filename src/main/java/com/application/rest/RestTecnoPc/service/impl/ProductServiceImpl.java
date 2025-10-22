package com.application.rest.RestTecnoPc.service.impl;

import com.application.rest.RestTecnoPc.entities.Product;
import com.application.rest.RestTecnoPc.persistence.IProductDAO;
import com.application.rest.RestTecnoPc.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private IProductDAO productDAO;

    @Override
    public Optional<Product> findById(Long id) {
        return productDAO.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public List<Product> findByPriceInRange(BigDecimal minprice, BigDecimal maxprice) {
        return productDAO.findByPriceInRange(minprice, maxprice);
    }

    @Override
    public void save(Product product) {
        productDAO.save(product);

    }

    @Override
    public void deleteById(Long id) {
        productDAO.deleteById(id);
    }
}
