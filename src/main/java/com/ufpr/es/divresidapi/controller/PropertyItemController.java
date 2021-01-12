package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.PropertyItemDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.PropertyItem;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.PropertyItemService;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;

@RestController
@RequestMapping("api/auth/inventory")
public class PropertyItemController 
	extends BaseRestController<PropertyItem, PropertyItemDTO, Property, Long> {

	@Autowired
	private PropertyItemService propertyItemService;
	
	
	@Override
	protected BaseResourceService<PropertyItemDTO, Long> getBaseResourceService() {
		return this.propertyItemService;
	}

	@Override
	protected LazyTableService<PropertyItem, Property> getLazyTableService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
