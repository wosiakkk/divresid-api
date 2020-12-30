package com.ufpr.es.divresidapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.CollectiveEntry;
import com.ufpr.es.divresidapi.model.User;

@Repository
public interface CollectiveEntryRepository 
	extends JpaRepository<CollectiveEntry, Long> {
	
	Page<CollectiveEntry> findAllByNameContainingAndUser(String name, User user,
			Pageable pageable);
	Page<CollectiveEntry> findAllByUser(Pageable pageable, User user);
	List<CollectiveEntry> findAllByUser(User user);
	long countByUser(User user);

}
