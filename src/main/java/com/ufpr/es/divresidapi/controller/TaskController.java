package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.TaskDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.Task;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.TaskService;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByProperty;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;

@RestController
@RequestMapping("api/auth/tasks")
public class TaskController 
	extends PropertyItemBaseRestController<Task, TaskDTO, Property, User, Long> {
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private LazyTableServiceByProperty<Task, Property> lazyTableService;
	

	@Override
	protected LazyTableServiceByProperty<Task, Property> getPropertyLazyTableService() {
		return this.lazyTableService;
	}

	@Override
	protected BaseResourceService<TaskDTO, Long> getBaseResourceService() {
		return this.taskService;
	}

	@Override
	protected LazyTableServiceByUser<Task, User> getLazyTableService() {
		return null;
	}

}
