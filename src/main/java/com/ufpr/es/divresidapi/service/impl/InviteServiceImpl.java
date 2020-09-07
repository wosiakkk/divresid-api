package com.ufpr.es.divresidapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.InviteConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.dto.InviteDTO;
import com.ufpr.es.divresidapi.model.Invite;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.InviteRepository;
import com.ufpr.es.divresidapi.service.InviteService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Service
public class InviteServiceImpl 
	extends BaseResourceServiceImpl<Invite, InviteDTO, Long>
	implements InviteService {
	
	@Autowired
	private InviteConverter inviteConverter;
	@Autowired
	private InviteRepository inviteRepository;
	
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
	
}
