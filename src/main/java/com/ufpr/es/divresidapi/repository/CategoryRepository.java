package com.ufpr.es.divresidapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Page<Category> findAllByNameContaining(String name, Pageable pageable);
}
