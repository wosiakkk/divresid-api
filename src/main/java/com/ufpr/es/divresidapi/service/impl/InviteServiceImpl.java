package com.ufpr.es.divresidapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class InviteServiceImpl 
	extends BaseResourceServiceImpl<Invite, InviteDTO, Long>
	implements InviteService {
	
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
		return null;
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
		
		boolean alreadyExistsInProperty = this.propertyService
				.existsResident(invite.getIdTo().getId(),
								invite.getIdProperty().getId());
		
		if(!alreadyExistsInProperty) { 
			this.update(this.inviteConverter.convertToDTO(invite));
			PropertyDTO property = this
					.propertyService.findById(invite.getIdProperty().getId());
			property.getResidents()
				.add(this.userConverter
							.convertToModel(this.userService
									.findById(invite.getIdTo().getId())));
			this.propertyService.update(property);
		}
		else {
			throw new ServiceException("Usuário já existe nesse imóvel");
		}
		
		UserDTO dto = this.userService.setNewRole("resident", invite.getIdTo().getId());
		
		System.out.println(dto);
		
		return this.inviteConverter.convertToDTO(invite);
	}
	
}
