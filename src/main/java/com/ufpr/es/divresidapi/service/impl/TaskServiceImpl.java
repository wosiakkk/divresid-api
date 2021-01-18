package com.ufpr.es.divresidapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.converter.TaskConverter;
import com.ufpr.es.divresidapi.dto.TaskDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.Task;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.TaskRepository;
import com.ufpr.es.divresidapi.service.TaskService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByProperty;

@Service
public class TaskServiceImpl 
	extends BaseResourceServiceImpl<Task, TaskDTO, Long>
	implements TaskService,
				LazyTableServiceByProperty<Task, Property>{
	
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private TaskConverter taskConverter;

	@Override
	public List<TaskDTO> findAllByUser(User user) throws ServiceException {
		List<TaskDTO> dtos = new ArrayList<>();
		List<Task> models = this.taskRepository.findAllByUser(user);
		models.forEach(m -> dtos.add(this.taskConverter.convertToDTO(m)));
		return dtos;
	}

	@Override
	protected ResourceConverter<Task, TaskDTO> getConverter() {
		return this.taskConverter;
	}

	@Override
	protected JpaRepository<Task, Long> getRepository() {
		return this.taskRepository;
	}

	@Override
	public Page<Task> listAllPageableAndProperty(Pageable pageable,
			Property property) throws ServiceException {
		return this.taskRepository.findAllByProperty(pageable, property);
	}

	@Override
	public Page<Task> findAllByNameContainingAndProperty(String searchString, Property property, Pageable pageable) {
		return this.taskRepository
				.findAllByDescriptionContainingAndProperty(
						searchString, property, pageable
				);
	}

	@Override
	public Long getNumberOfEntities(Property t) throws ServiceException {
		return this.taskRepository.countByProperty(t);
	}

}
