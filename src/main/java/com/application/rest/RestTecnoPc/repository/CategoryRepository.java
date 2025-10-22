package com.application.rest.RestTecnoPc.repository;

import com.application.rest.RestTecnoPc.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
