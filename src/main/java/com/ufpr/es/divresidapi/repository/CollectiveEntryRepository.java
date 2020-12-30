package com.ufpr.es.divresidapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.CollectiveEntry;

@Repository
public interface CollectiveEntryRepository 
	extends JpaRepository<CollectiveEntry, Long> {

}
