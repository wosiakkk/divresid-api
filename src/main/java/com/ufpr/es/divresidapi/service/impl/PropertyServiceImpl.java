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
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;

@Service
public class PropertyServiceImpl 
	extends BaseResourceServiceImpl<Property, PropertyDTO, Long> 
	implements	PropertyService,
				LazyTableServiceByUser<Property, User> {
	
	@Autowired
	private PropertyConverter propertyConverter;
	@Autowired
	private PropertyRepository propertyrepository;
	@Autowired
	private InviteServiceImpl inviteService;
	
	
	@Override
	public PropertyDTO save(PropertyDTO dto) throws ServiceException {
		Property property = propertyrepository
				.save(propertyConverter.convertToModel(dto));
		//adding adm as resident in property
		inviteService
			.addAdmAsResidentToPropertyInCreate(
					property.getId(),
					property.getUser().getId()
			);
		return propertyConverter.convertToDTO(property);
	}

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
	
	@Override
	public PropertyDTO getCurrentActiveProperty(Long userId) {
		return this.propertyConverter
				.convertToDTO(this.propertyrepository
									.getCurrentActiveProperty(userId));
	}
	
	@Override
	public PropertyDTO getCurrentActivePropertyOfResident(Long userId) {
		return this.propertyConverter
				.convertToDTO(this.propertyrepository
									.getCurrentActivePropertyOfResident(userId));
	}


	@Override
	public void setNonActivePropertiesToFalse(Long idActive) {
		this.propertyrepository.setNonActivePropertiesToFalse(idActive);
	}

	@Override
	public void removeResidentFromProperty(Long userId, Long propertyId) {
		this.propertyrepository.removeResident(userId, propertyId);
	}

}
