package com.ufpr.es.divresidapi.service;

import com.ufpr.es.divresidapi.dto.PropertyItemDTO;
import com.ufpr.es.divresidapi.model.PropertyItemImage;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface PropertyItemService 
	extends BaseResourceService<PropertyItemDTO, Long>{
	
	PropertyItemImage saveImage(PropertyItemImage image, Long propertyId) 
			throws ServiceException;

}
