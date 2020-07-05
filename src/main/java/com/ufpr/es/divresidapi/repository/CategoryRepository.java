package com.ufpr.es.divresidapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.model.User;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	Page<Category> findAllByNameContainingAndUser(String name,User user, 
			Pageable pageable);
	Page<Category> findAllByUser(Pageable pageable,User user);
	List<Category> findAllByUser(User user);
	long countByUser(User user);
	
}
