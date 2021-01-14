package com.ufpr.es.divresidapi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;

import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByProperty;

public abstract class PropertyItemBaseRestController
	<TENTITY, TDTO,TENTITYCOUNT,TENTITYCOUNTUSER, TID> 
	extends BaseRestController<TENTITY, TDTO, TENTITYCOUNTUSER, TID>{

	protected abstract LazyTableServiceByProperty<TENTITY, TENTITYCOUNT>
		getPropertyLazyTableService();
	
	
	@GetMapping(value = "/pagination/items")
	@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Page<TENTITY>> listAllPageableItems(Pageable pageable,
			String searchString, Property property){
		
		try {
			if(isFilteredSearch(searchString))
				return ResponseEntity
						.ok(this.getPropertyLazyTableService()
								.findAllByNameContainingAndProperty(
										searchString, property, pageable));
			
			Pageable sortedByNameAsc = PageRequest
					.of(pageable.getPageNumber(), pageable.getPageSize(),
							Sort.by("name").ascending());
			return ResponseEntity.ok(this.getPropertyLazyTableService()
					.listAllPageableAndProperty(sortedByNameAsc, property));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/pagination/items/count")
	@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Long> getNumberOfItems(TENTITYCOUNT t){
		try {
			return ResponseEntity.ok(this.getPropertyLazyTableService()
					.getNumberOfEntities(t));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	protected boolean isFilteredSearch(String param) {
		return !param.equalsIgnoreCase("not");
	}
	
}
