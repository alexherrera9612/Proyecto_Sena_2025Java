package com.application.rest.RestTecnoPc.persistence;

import com.application.rest.RestTecnoPc.entities.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    Optional<Product> findById(Long id);
    List<Product> findAll();

    List<Product> findByPriceInRange(BigDecimal minprice, BigDecimal maxprice);
    void save (Product product);
    void deleteById(Long id);

}
