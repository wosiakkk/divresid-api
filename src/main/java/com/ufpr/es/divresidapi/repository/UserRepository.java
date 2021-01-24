package com.ufpr.es.divresidapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);
	Optional<User> findByEmail(String email);
	Optional<User> findByPasswordAndId(String password, Long id);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
//	long countByProperty(Property property);
	@Modifying
	@Query("delete from User u where u.username = :username")
	void deleteByusername(String username);
	
}
