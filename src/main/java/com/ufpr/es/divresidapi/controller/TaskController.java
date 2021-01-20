package com.ufpr.es.divresidapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.TaskDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.Task;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.TaskService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
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
	protected LazyTableServiceByProperty<Task, Property> 
		getPropertyLazyTableService() {
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
	
	@PutMapping(value = "/done")
	@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Boolean> updateTaskStatus(@RequestBody Long id){
		try {
			this.taskService.updateTaskStatus(id, true);
			return ResponseEntity.ok(true);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/active")
	@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<TaskDTO>> getAllActive(
			@RequestParam() Long property){
		try {
			return ResponseEntity.ok(this.taskService.getAllActive(property));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
