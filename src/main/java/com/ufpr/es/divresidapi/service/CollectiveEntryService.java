package com.ufpr.es.divresidapi.service;

import com.ufpr.es.divresidapi.dto.CollectiveEntryDTO;
import com.ufpr.es.divresidapi.model.CollectiveEntry;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface CollectiveEntryService 
	extends BaseResourceService<CollectiveEntryDTO, Long>{

	CollectiveEntry saveModel(CollectiveEntry model) throws ServiceException;
}
