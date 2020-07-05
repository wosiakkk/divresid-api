package com.ufpr.es.divresidapi.service;

import java.util.List;

import com.ufpr.es.divresidapi.dto.EntryDTO;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface EntryService extends BaseResourceService<EntryDTO, Long> {
	
	List<EntryDTO> findAllByUserAndMonthAndYear
		(Long user, Integer month, Integer year) throws ServiceException;
	
}
