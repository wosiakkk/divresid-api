package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.PropertyDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.PropertyService;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;

@RestController
@RequestMapping("api/auth/properties")
public class PropertyController 
	extends BaseRestController<Property, PropertyDTO, User, Long>{
	
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private LazyTableService<Property, User> lazyTableservice;

	@Override
	protected BaseResourceService<PropertyDTO, Long> getBaseResourceService() {
		return this.propertyService;
	}

	@Override
	protected LazyTableService<Property, User> getLazyTableService() {
		return this.lazyTableservice;
	}
	
	@GetMapping(value = "/currentActive")
	@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Property> getCurrentActiveProperty(
			@RequestParam() Long userId){
		try {
			return ResponseEntity
					.ok(this.propertyService.getCurrentActiveProperty(userId));
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
