package com.ufpr.es.divresidapi.service;

import com.ufpr.es.divresidapi.dto.TaskDTO;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface TaskService extends BaseResourceService<TaskDTO, Long>{
	
	void updateTaskStatus(Long id, boolean status) throws ServiceException;

}
