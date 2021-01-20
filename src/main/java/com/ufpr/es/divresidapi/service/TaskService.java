package com.ufpr.es.divresidapi.service;

import java.util.List;

import com.ufpr.es.divresidapi.dto.TaskDTO;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface TaskService extends BaseResourceService<TaskDTO, Long>{
	
	void updateTaskStatus(Long id, boolean status) throws ServiceException;
	List<TaskDTO> getAllActive(Long propertyId) throws ServiceException;

}
