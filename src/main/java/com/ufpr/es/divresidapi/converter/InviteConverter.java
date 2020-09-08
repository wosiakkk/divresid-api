package com.ufpr.es.divresidapi.converter;

import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.dto.InviteDTO;
import com.ufpr.es.divresidapi.model.Invite;

@Component
public class InviteConverter implements ResourceConverter<Invite, InviteDTO>{

	@Override
	public Invite convertToModel(InviteDTO dto) {
		return new Invite(dto.getId(), dto.getIdFrom(), 
				dto.getIdTo(), dto.getIdProperty(), dto.isAccepted());
	}

	@Override
	public InviteDTO convertToDTO(Invite model) {
		return new InviteDTO(model.getId(), model.getIdFrom(), 
				model.getIdTo(), model.getIdProperty(), model.isAccepted());
	}

}
