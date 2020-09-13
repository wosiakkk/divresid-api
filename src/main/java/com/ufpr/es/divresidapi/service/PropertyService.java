package com.ufpr.es.divresidapi.service;

import com.ufpr.es.divresidapi.dto.PropertyDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface PropertyService extends BaseResourceService<PropertyDTO, Long>{
	
	boolean existsResident(Long userId, Long propertyId);
	PropertyDTO getCurrentActiveProperty(Long userId);
	void setNonActivePropertiesToFalse(Long idActive);
	void removeResidentFromProperty
				(Long userId, Long propertyId) throws ServiceException;
	
}
