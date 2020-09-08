package com.ufpr.es.divresidapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;

@Service
public class PropertyServiceImpl 
	extends BaseResourceServiceImpl<Property, PropertyDTO, Long> 
	implements	PropertyService,
				LazyTableService<Property, User> {
	
	@Autowired
	private PropertyConverter propertyConverter;
	
	@Autowired
	private PropertyRepository propertyrepository;

	@Override
	public List<PropertyDTO> findAllByUser(User user) throws ServiceException {
		List<PropertyDTO> dtos = new ArrayList<>();
		List<Property> models = this.propertyrepository.findAllByUser(user);
		models.forEach(
				model -> 
					dtos.add(this.propertyConverter.convertToDTO(model)));
		return dtos;
	}

	@Override
	protected ResourceConverter<Property, PropertyDTO> getConverter() {
		return this.propertyConverter;
	}

	@Override
	protected JpaRepository<Property, Long> getRepository() {
		return this.propertyrepository;
	}

	@Override
	public Page<Property> listAllPageableAndUser(Pageable pageable, User user)
			throws ServiceException {
		return this.propertyrepository.findAllByUser(pageable, user);
	}

	@Override
	public Page<Property> findAllByNameContainingAndUser(String searchString,
			User user, Pageable pageable) {
		return this.propertyrepository
				.findAllByNameContainingAndUser(searchString, user, pageable);
	}

	@Override
	public Long getNumberOfEntities(User user) throws ServiceException {
		return this.propertyrepository.countByUser(user);
	}

	@Override
	public boolean existsResident(Long userId, Long propertyId) {
		return this.propertyrepository.existsResident(userId, propertyId);
	}

}
