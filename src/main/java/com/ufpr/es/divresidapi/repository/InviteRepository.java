package com.ufpr.es.divresidapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Invite;

@Repository
public interface InviteRepository extends JpaRepository<Invite, Long>{

}
