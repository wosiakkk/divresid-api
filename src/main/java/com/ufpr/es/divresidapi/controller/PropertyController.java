package com.ufpr.es.divresidapi.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.PropertyDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.PropertyService;
import com.ufpr.es.divresidapi.service.UserService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;

@RestController
@RequestMapping("api/auth/properties")
public class PropertyController 
	extends BaseRestController<Property, PropertyDTO, User, Long>{
	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private UserService userSerive;
	@Autowired
	private LazyTableServiceByUser<Property, User> lazyTableservice;

	@Override
	protected BaseResourceService<PropertyDTO, Long> getBaseResourceService() {
		return this.propertyService;
	}

	@Override
	protected LazyTableServiceByUser<Property, User> getLazyTableService() {
		return this.lazyTableservice;
	}
	
	@Transactional
	@GetMapping(value = "/currentActive")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PropertyDTO> getCurrentActiveProperty(
			@RequestParam() Long userId){
		try {
			
			PropertyDTO p = this.propertyService.getCurrentActiveProperty(userId);
			return ResponseEntity.ok(p);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Transactional
	@GetMapping(value = "/currentActiveOfResident")
	@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<PropertyDTO> getCurrentActivePropertyOfResident(
			@RequestParam() Long userId){
		try {
			
			PropertyDTO p = this.propertyService
					.getCurrentActivePropertyOfResident(userId);
			return ResponseEntity.ok(p);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	@Transactional
	@PostMapping
	@PreAuthorize("hasRole('ADMIN') or hasRole('RESIDENT')")
	public ResponseEntity<PropertyDTO> save(
			@RequestBody PropertyDTO dto) {
		try {
			PropertyDTO savedDto = this.propertyService.save(dto);
			if(savedDto.isActive())
				this.propertyService
					.setNonActivePropertiesToFalse(savedDto.getId());
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(savedDto);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	@Transactional
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<PropertyDTO> update(
			@RequestBody PropertyDTO dto) {
		try {
			PropertyDTO updatedDto = this.propertyService.update(dto);
			if(updatedDto.isActive())
				this.propertyService
					.setNonActivePropertiesToFalse(updatedDto.getId());
			return ResponseEntity.status(HttpStatus.OK)
					.body(updatedDto);
		}catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	@DeleteMapping(value = "/removeResident")
	@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<PropertyDTO> removeResidentFromProperty(
			@RequestParam() Long userId,
			@RequestParam() Long propertyId){
		try {
			this.propertyService.removeResidentFromProperty(userId, propertyId);
			this.userSerive.setNewRole("admin", userId);
			return ResponseEntity.ok().body(null);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
