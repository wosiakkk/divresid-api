package com.ufpr.es.divresidapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.PropertyItemImage;

@Repository
public interface PropertyItemImageRepository 
	extends JpaRepository<PropertyItemImage, Long> {

}
