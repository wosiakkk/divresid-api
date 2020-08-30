package com.ufpr.es.divresidapi.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.LazyTableService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;



public abstract class BaseRestController<TENTITY, TDTO, TID> {
	
	protected abstract BaseResourceService< TDTO, TID> getBaseResourceService();
	protected abstract LazyTableService<TENTITY> getLazyTableService();
	

	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<TDTO>> listAll(){
		try {
			return ResponseEntity.ok(this.getBaseResourceService().findAll());
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/resources/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<TDTO>> listAllByUser(User user){
		try {
			return ResponseEntity.ok(this.getBaseResourceService()
					.findAllByUser(user));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/pagination")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Page<TENTITY>> listAllPageable(Pageable pageable,
			String searchString,User user){
		try {
			if(isFilteredSearch(searchString))
				return ResponseEntity
						.ok(this.getLazyTableService()
								.findAllByNameContainingAndUser(searchString,
																user, 
																pageable));
				
			Pageable sortedByNameAsc = PageRequest
					.of(pageable.getPageNumber(), pageable.getPageSize(),
							Sort.by("name").ascending());
			return ResponseEntity.ok(this.getLazyTableService()
						.listAllPageableAndUser(sortedByNameAsc, user));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/pagination/count")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Long> getNumberOfEntities(User user){
		try {
			return ResponseEntity.ok(this.getLazyTableService()
					.getNumberOfEntities(user));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<TDTO> findById(@PathVariable(name = "id") TID id ){
		try {
			return ResponseEntity.ok(this.getBaseResourceService().findById(id));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<TDTO> save(@RequestBody TDTO dto){
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(this.getBaseResourceService().save(dto));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<TDTO> update(@RequestBody TDTO dto){
		try {
			return ResponseEntity.ok(this.getBaseResourceService().save(dto));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<TDTO> delete(@PathVariable(name = "id") TID id){
		try {
			this.getBaseResourceService().delete(id);
			return ResponseEntity.ok().body(null);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private boolean isFilteredSearch(String param) {
		return !param.equalsIgnoreCase("not");
	}
}
