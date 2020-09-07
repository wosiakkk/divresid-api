package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.InviteDTO;
import com.ufpr.es.divresidapi.model.Invite;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.InviteService;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;

@RestController
@RequestMapping(value = "api/auth/invites")
public class InviteController 
	extends BaseRestController<Invite, InviteDTO, User, Long>{
	
	@Autowired
	private InviteService inviteService;
	@Autowired
	private LazyTableService<Invite, User> lazyTableService;
	
	@Override
	protected BaseResourceService<InviteDTO, Long> getBaseResourceService() {
		return this.inviteService;
	}

	@Override
	protected LazyTableService<Invite, User> getLazyTableService() {
		return this.lazyTableService;
	}

}
