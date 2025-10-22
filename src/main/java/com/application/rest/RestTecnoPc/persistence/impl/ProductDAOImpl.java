package com.application.rest.RestTecnoPc.persistence.impl;

import com.application.rest.RestTecnoPc.entities.Product;
import com.application.rest.RestTecnoPc.persistence.IProductDAO;
import com.application.rest.RestTecnoPc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@Component
public class ProductDAOImpl implements IProductDAO {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public List<Product> findByPriceInRange(BigDecimal minprice, BigDecimal maxprice) {
        return productRepository.findProductByPriceInRange(minprice, maxprice);
    }

    @Override
    public void save(Product product) {

        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
