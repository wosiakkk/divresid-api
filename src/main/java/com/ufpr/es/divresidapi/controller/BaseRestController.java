package com.ufpr.es.divresidapi.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;



public abstract class BaseRestController<TDTO, TID> {
	
	protected abstract BaseResourceService<TDTO, TID> getBaseResourceService();
	
	@GetMapping
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<TDTO>> listAll(){
		try {
			return ResponseEntity.ok(this.getBaseResourceService().listAll());
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
			return ResponseEntity.status(HttpStatus.CREATED).body(this.getBaseResourceService().save(dto));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping
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
	

}