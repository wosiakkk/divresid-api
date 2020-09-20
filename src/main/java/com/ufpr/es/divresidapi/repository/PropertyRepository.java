package com.ufpr.es.divresidapi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

	Page<Property> findAllByNameContainingAndUser(String name,User user, 
			Pageable pageable);
	Page<Property> findAllByUser(Pageable pageable, User user);
	List<Property> findAllByUser(User user);
	long countByUser(User user);
	
	@Query(
		nativeQuery = true,
		value = "SELECT CASE WHEN COUNT(p)>0 "
				+ "THEN TRUE ELSE FALSE END "
				+ "FROM property_residents p "
				+ "WHERE p.user_id = ?1 and p.property_id= ?2"
	)
	boolean existsResident(Long userId, Long propertyId);
	
	@Modifying
	@Query(
		nativeQuery = true,
		value = "DELETE FROM property_residents p "
				+ "WHERE p.user_id = ?1 AND p.property_id = ?2"
	)
	void removeResident(Long userId, Long propertyId);
	
	@Query(
		nativeQuery = true,
		value = "SELECT * FROM property "
				+ "WHERE active = true AND property.user_id = ?1"
	)
	Property getCurrentActiveProperty(Long userId);
	
	@Query(
		nativeQuery = true,
		value = "SELECT * FROM property INNER JOIN property_residents "
				+ "ON property.id = property_residents.property_id "
				+ "AND property_residents.user_id = ?1 "
				+ "WHERE property.active = true;"
	)
	Property getCurrentActivePropertyOfResident(Long userId);
	
	@Modifying
	@Query(
		nativeQuery = true,
		value = "UPDATE property SET active = false "
				+ "WHERE id != ?1"
	)
	void setNonActivePropertiesToFalse(Long idActive);
}
