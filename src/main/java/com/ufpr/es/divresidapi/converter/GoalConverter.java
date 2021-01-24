package com.ufpr.es.divresidapi.converter;

import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.dto.GoalDTO;
import com.ufpr.es.divresidapi.model.Goal;

@Component
public class GoalConverter implements ResourceConverter<Goal, GoalDTO> {

	@Override
	public Goal convertToModel(GoalDTO dto) {
		return new Goal(dto.getId(), dto.getName(), dto.getValue(), 
				dto.getDate(), dto.getUser(), dto.isDone());
	}

	@Override
	public GoalDTO convertToDTO(Goal model) {
		return new GoalDTO(model.getId(), model.getName(), 
				model.getValue(), model.getDate(), model.getUser(),
				model.isDone());
	}

}
