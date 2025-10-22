package com.application.rest.RestTecnoPc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.application.rest.RestTecnoPc.entities.Subcategory;

@Repository
public interface SubCategoryRepository extends CrudRepository<Subcategory,Long> {

}
