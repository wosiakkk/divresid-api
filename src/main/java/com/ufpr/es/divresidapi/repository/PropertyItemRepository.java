package com.ufpr.es.divresidapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.PropertyItem;
import com.ufpr.es.divresidapi.model.User;

@Repository
public interface PropertyItemRepository 
	extends JpaRepository<PropertyItem, Long>{
	
	Page<PropertyItem> findAllByNameContainingAndProperty(String name, 
			Property property, Pageable pageable);
	Page<PropertyItem> findAllByUser(Pageable pageable, User user);
	Page<PropertyItem> listAllPageableAndProperty(Pageable pageable, 
			Property property);
	Long countByProperty(Property property);

}
