package com.ufpr.es.divresidapi.service;

import java.util.List;

import com.ufpr.es.divresidapi.dto.EntryDTO;
import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface EntryService extends BaseResourceService<EntryDTO, Long> {
	
	List<Entry> findAllByUserAndMonthAndYear
		(Long user, Integer month, Integer year) throws ServiceException;
	
}
