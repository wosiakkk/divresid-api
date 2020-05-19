package com.ufpr.es.divresidapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long>{

}
