package com.ufpr.es.divresidapi.service;

import com.ufpr.es.divresidapi.dto.InviteDTO;
import com.ufpr.es.divresidapi.model.Invite;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface InviteService extends BaseResourceService<InviteDTO, Long>{
	
	InviteDTO acceptInvite(Invite inviteId) throws ServiceException;
	
}
