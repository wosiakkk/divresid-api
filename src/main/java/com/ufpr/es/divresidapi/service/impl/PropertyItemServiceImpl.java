package com.ufpr.es.divresidapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.PropertyItemConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.dto.PropertyItemDTO;
import com.ufpr.es.divresidapi.model.PropertyItem;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.PropertyItemRepository;
import com.ufpr.es.divresidapi.service.PropertyItemService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Service
public class PropertyItemServiceImpl 
	extends BaseResourceServiceImpl<PropertyItem, PropertyItemDTO, Long>
	implements PropertyItemService {
	
	@Autowired
	private PropertyItemConverter propertyItemConverter;
	@Autowired
	private PropertyItemRepository propertyItemRepository;

	@Override
	public List<PropertyItemDTO> findAllByUser(User user) 
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ResourceConverter<PropertyItem, PropertyItemDTO> getConverter() {
		return this.propertyItemConverter;
	}

	@Override
	protected JpaRepository<PropertyItem, Long> getRepository() {
		return this.propertyItemRepository;
	}
	

}
