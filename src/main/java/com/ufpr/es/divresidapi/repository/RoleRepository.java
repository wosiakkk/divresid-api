package com.ufpr.es.divresidapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpr.es.divresidapi.model.ERole;
import com.ufpr.es.divresidapi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByName(ERole name);

}
