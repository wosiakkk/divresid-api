package com.ufpr.es.divresidapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpr.es.divresidapi.model.Property;

public interface PropertyRepository extends JpaRepository<Property, Long>{

}
