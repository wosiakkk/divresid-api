package com.ufpr.es.divresidapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.InviteConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.converter.UserConverter;
import com.ufpr.es.divresidapi.dto.InviteDTO;
import com.ufpr.es.divresidapi.dto.PropertyDTO;
import com.ufpr.es.divresidapi.dto.UserDTO;
import com.ufpr.es.divresidapi.model.Invite;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.InviteRepository;
import com.ufpr.es.divresidapi.service.InviteService;
import com.ufpr.es.divresidapi.service.PropertyService;
import com.ufpr.es.divresidapi.service.UserService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;

@Service
public class InviteServiceImpl 
	extends BaseResourceServiceImpl<Invite, InviteDTO, Long>
	implements InviteService,
				LazyTableServiceByUser<Invite, User>{
	
	@Autowired
	private InviteConverter inviteConverter;
	@Autowired
	private InviteRepository inviteRepository;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public List<InviteDTO> findAllByUser(User user) throws ServiceException {
		List<InviteDTO> dtos =  new ArrayList<InviteDTO>();
		List<Invite> models = this.inviteRepository
									.findAllByIdFromOrIdTo(user,user);
		models.forEach(i -> {
			dtos.add(this.inviteConverter.convertToDTO(i));
		});
		return dtos;
	}

	@Override
	protected ResourceConverter<Invite, InviteDTO> getConverter() {
		return this.inviteConverter;
	}

	@Override
	protected JpaRepository<Invite, Long> getRepository() {
		return this.inviteRepository;
	}

	@Override
	public InviteDTO acceptInvite(Invite invite) throws ServiceException {
		if(!alreadyExistsInProperty(invite)) {
			invite.setAcceted(true);
			this.update(this.inviteConverter.convertToDTO(invite));
			addResidentToProperty(invite);
		}
		else {
			throw new ServiceException("Usuário já existe nesse imóvel");
		}
		updateResidentRole(invite);
		return this.inviteConverter.convertToDTO(invite);
	}
	
	@Override
	public Page<Invite> listAllPageableAndUser(Pageable pageable, User user)
			throws ServiceException {
		return this.inviteRepository
				.findAllByIdFromOrIdTo(pageable, user,user);
	}

	@Override
	public Page<Invite> findAllByNameContainingAndUser(String searchString, 
			User user, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNumberOfEntities(User t) throws ServiceException {
		return this.inviteRepository.countByIdFromOrIdTo(t, t);
	}
	
	private boolean alreadyExistsInProperty(Invite invite) {
		return this.propertyService
						.existsResident(invite.getIdTo().getId(),
										invite.getIdProperty().getId());
	}
	
	private void addResidentToProperty(Invite invite) throws ServiceException {
		PropertyDTO property = this.propertyService
				.findById(invite.getIdProperty().getId());
		property.getResidents().add(this.userConverter
				.convertToModel(this.userService
				.findById(invite.getIdTo().getId())));
		
		this.propertyService.update(property);
	}
	
	protected void addAdmAsResidentToPropertyInCreate
		(Long propertyId, Long admId) throws ServiceException {
		PropertyDTO property = this.propertyService
				.findById(propertyId);
		property.setResidents(new ArrayList<>());
		property.getResidents().add(this.userConverter
				.convertToModel(this.userService.findById(admId)));
		this.propertyService.update(property);
	}
	
	private void updateResidentRole(Invite invite) throws ServiceException {
		this.userService.setNewRole("resident", invite.getIdTo().getId());
	}
	
}
