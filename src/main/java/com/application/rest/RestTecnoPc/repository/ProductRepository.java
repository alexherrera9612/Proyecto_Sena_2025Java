package com.application.rest.RestTecnoPc.repository;

import com.application.rest.RestTecnoPc.entities.Product;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minprice AND :maxprice")
    List<Product> findProductByPriceInRange(BigDecimal minprice, BigDecimal maxprice);

}
