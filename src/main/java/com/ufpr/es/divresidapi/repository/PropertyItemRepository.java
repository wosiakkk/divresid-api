package com.ufpr.es.divresidapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.PropertyItem;

@Repository
public interface PropertyItemRepository 
	extends JpaRepository<PropertyItem, Long>{

}
