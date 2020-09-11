package com.ufpr.es.divresidapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Invite;
import com.ufpr.es.divresidapi.model.User;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long>{
	
	List<Invite> findAllByIdFromOrIdTo(User idFrom, User idTo);

	Page<Invite> findAllByIdFromOrIdTo(Pageable pageable,User idFrom, User idTo);
	
	long countByIdFromOrIdTo(User idFrom, User idTo);
}
