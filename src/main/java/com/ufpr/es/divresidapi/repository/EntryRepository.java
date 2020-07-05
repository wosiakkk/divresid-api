package com.ufpr.es.divresidapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Query(
		nativeQuery = true,
		value = "SELECT * FROM entries e "
				+ "WHERE e.user_id=?1 "
				+ "AND EXTRACT(MONTH FROM e.date)=?2 "
				+ "AND EXTRACT(YEAR FROM e.date)=?3 "
	)
	List<Entry> findAllByUserAndMonthAndYear
		(Long userId, Integer month, Integer year);

}
