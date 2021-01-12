package com.ufpr.es.divresidapi.service;

import java.util.List;

import com.ufpr.es.divresidapi.dto.CollectiveEntryDTO;
import com.ufpr.es.divresidapi.model.CollectiveEntry;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface CollectiveEntryService 
	extends BaseResourceService<CollectiveEntryDTO, Long>{

	CollectiveEntry saveModel(CollectiveEntry model) throws ServiceException;
	
	List<CollectiveEntryDTO> findAllByUserAndMonthAndYear
		(Long user, Integer month, Integer year) throws ServiceException;
}
