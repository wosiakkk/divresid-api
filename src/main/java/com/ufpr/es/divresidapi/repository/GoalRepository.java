package com.ufpr.es.divresidapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Goal;
import com.ufpr.es.divresidapi.model.User;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long>{
	
	Page<Goal> findAllByNameContainingAndUser(String name, User user,
			Pageable pageable);
	Page<Goal> findAllByUser(Pageable pageable,User user);
	List<Goal> findAllByUser(User user);
	long countByUser(User user);

}
