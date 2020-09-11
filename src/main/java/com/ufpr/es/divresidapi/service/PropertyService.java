package com.ufpr.es.divresidapi.service;

import com.ufpr.es.divresidapi.dto.PropertyDTO;

public interface PropertyService extends BaseResourceService<PropertyDTO, Long>{
	
	boolean existsResident(Long userId, Long propertyId);
	Long getCurrentActiveProperty(Long userId);
	
}
