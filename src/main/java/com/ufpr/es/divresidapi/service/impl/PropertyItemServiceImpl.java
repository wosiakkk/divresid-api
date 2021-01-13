package com.ufpr.es.divresidapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.PropertyItemConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.dto.PropertyItemDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.PropertyItem;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.PropertyItemRepository;
import com.ufpr.es.divresidapi.service.PropertyItemService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByProperty;

@Service
public class PropertyItemServiceImpl 
	extends BaseResourceServiceImpl<PropertyItem, PropertyItemDTO, Long>
	implements PropertyItemService,
			   LazyTableServiceByProperty<PropertyItem, Property>{
	
	@Autowired
	private PropertyItemConverter propertyItemConverter;
	@Autowired
	private PropertyItemRepository propertyItemRepository;



	@Override
	protected ResourceConverter<PropertyItem, PropertyItemDTO> getConverter() {
		return this.propertyItemConverter;
	}

	@Override
	protected JpaRepository<PropertyItem, Long> getRepository() {
		return this.propertyItemRepository;
	}

	@Override
	public Page<PropertyItem> listAllPageableAndProperty(Pageable pageable, 
			Property property) throws ServiceException {
		return this.propertyItemRepository
				.listAllPageableAndProperty(pageable, property);
	}

	@Override
	public Page<PropertyItem> findAllByNameContainingAndProperty(
			String searchString, Property property,
			Pageable pageable) {
		return this.propertyItemRepository
				.findAllByNameContainingAndProperty(
					searchString, property, pageable
				);
	}
	
	@Override
	public List<PropertyItemDTO> findAllByUser(User user) 
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNumberOfEntities(Property property) throws ServiceException {
		return propertyItemRepository.countByProperty(property);
	}

}
