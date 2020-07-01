package com.ufpr.es.divresidapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.model.User;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long>{
	
	Page<Entry> findAllByNameContainingAndUser(String name,User user, 
			Pageable pageable);
	Page<Entry> findAllByUser(Pageable pageable,User user);
	List<Entry> findAllByUser(User user);
	long countByUser(User user);

}
