package com.ufpr.es.divresidapi.service;

import com.ufpr.es.divresidapi.dto.PropertyDTO;
import com.ufpr.es.divresidapi.model.Property;

public interface PropertyService extends BaseResourceService<PropertyDTO, Long>{
	
	boolean existsResident(Long userId, Long propertyId);
	Property getCurrentActiveProperty(Long userId);
	
}
