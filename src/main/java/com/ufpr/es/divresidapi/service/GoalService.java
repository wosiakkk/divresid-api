package com.ufpr.es.divresidapi.service;

import java.util.List;

import com.ufpr.es.divresidapi.dto.GoalDTO;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface GoalService extends BaseResourceService<GoalDTO, Long>{
	
	List<GoalDTO> getAllActive(Long userId) throws ServiceException;

}
