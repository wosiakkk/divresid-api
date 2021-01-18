package com.ufpr.es.divresidapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.Task;
import com.ufpr.es.divresidapi.model.User;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	
	Page<Task> findAllByDescriptionContainingAndProperty(String description,
			Property property, Pageable pageable);
	Page<Task> findAllByProperty(Pageable pageable,
			Property property);
	List<Task> findAllByUser(User user);
	Page<Task> findAllByUser(Pageable pageable, User user);
	Long countByProperty(Property property);

}
