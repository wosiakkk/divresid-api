package com.ufpr.es.divresidapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

	Page<Property> findAllByNameContainingAndUser(String name,User user, 
			Pageable pageable);
	Page<Property> findAllByUser(Pageable pageable, User user);
	List<Property> findAllByUser(User user);
	long countByUser(User user);
	
}
