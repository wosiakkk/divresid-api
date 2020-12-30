package com.ufpr.es.divresidapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpr.es.divresidapi.model.CollectiveEntry;

public interface CollectiveEntryRepository 
	extends JpaRepository<CollectiveEntry, Long> {

}
