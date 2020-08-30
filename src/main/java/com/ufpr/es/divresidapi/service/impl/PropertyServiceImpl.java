package com.ufpr.es.divresidapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.PropertyConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.dto.PropertyDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.PropertyRepository;
import com.ufpr.es.divresidapi.service.PropertyService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Service
public class PropertyServiceImpl 
	extends BaseResourceServiceImpl<Property, PropertyDTO, Long> 
	implements PropertyService {
	
	@Autowired
	private PropertyConverter propertyConverter;
	
	@Autowired
	private PropertyRepository propertyrepository;

	@Override
	public List<PropertyDTO> findAllByUser(User user) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ResourceConverter<Property, PropertyDTO> getConverter() {
		return this.propertyConverter;
	}

	@Override
	protected JpaRepository<Property, Long> getRepository() {
		return this.propertyrepository;
	}

}
