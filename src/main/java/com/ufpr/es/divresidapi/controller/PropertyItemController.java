package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.PropertyItemDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.PropertyItem;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.PropertyItemService;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByProperty;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;

@RestController
@RequestMapping("api/auth/inventory")
public class PropertyItemController 
	extends PropertyItemBaseRestController<PropertyItem, 
		PropertyItemDTO, Property,User, Long> {

	@Autowired
	private PropertyItemService propertyItemService;
	@Autowired
	private LazyTableServiceByProperty<PropertyItem, Property> lazyTableService;
	
	
	
	@Override
	protected BaseResourceService<PropertyItemDTO, Long> 
		getBaseResourceService() {
		return this.propertyItemService;
	}

	@Override
	protected LazyTableServiceByProperty<PropertyItem, Property> 
		getPropertyLazyTableService() {
		return this.lazyTableService;
	}

	@Override
	protected LazyTableServiceByUser<PropertyItem, User> getLazyTableService() {
		return null;
	}
	
	
}
