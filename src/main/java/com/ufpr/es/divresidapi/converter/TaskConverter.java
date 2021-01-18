package com.ufpr.es.divresidapi.converter;

import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.dto.TaskDTO;
import com.ufpr.es.divresidapi.model.Task;

@Component
public class TaskConverter implements ResourceConverter<Task, TaskDTO> {

	@Override
	public Task convertToModel(TaskDTO dto) {
		return new Task(dto.getId(), dto.getName(), dto.isDone(),
				dto.getDate(), dto.getTargetUser(), 
				dto.getProperty(), dto.getUser());
	}

	@Override
	public TaskDTO convertToDTO(Task model) {
		return new TaskDTO(model.getId(), model.getName(),
				model.isDone(), model.getDate(), model.getTargetUser(),
				model.getProperty(), model.getUser());
	}

}
