package com.ufpr.es.divresidapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Modifying
	@Transactional
	@Query(
		nativeQuery = true,
		value = "DELETE FROM collective_entries c "
				+ "WHERE c.collective_id = ?1 AND c.entry_id = ?2 "
	)
	void deleteGeneratedEntryAssoc(Long collectiveId, Long entryId);

	@Query(
			nativeQuery = true,
			value = "SELECT * FROM collective_entries e "
					+ "WHERE e.user_id=?1 "
					+ "AND EXTRACT(MONTH FROM e.date)=?2 "
					+ "AND EXTRACT(YEAR FROM e.date)=?3 "
		)
	List<CollectiveEntry> findAllByUserAndMonthAndYear
		(Long userId, Integer month, Integer year);
	
	@Query(
			nativeQuery = true,
			value = "SELECT * FROM collective_entries e "
					+ "WHERE e.user_id=?1 "
					+ "AND EXTRACT(MONTH FROM e.date)=?2 "
					+ "AND EXTRACT(YEAR FROM e.date)=?3 /*#{#pageable}*/"
		)
	Page<CollectiveEntry> findAllByUserAndMonthAndYear
			(Long userId, Integer month, Integer year,Pageable pageable);
	
	
}
