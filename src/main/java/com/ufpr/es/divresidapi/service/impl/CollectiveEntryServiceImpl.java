package com.ufpr.es.divresidapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.CollectiveEntryConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.dto.CollectiveEntryDTO;
import com.ufpr.es.divresidapi.model.CollectiveEntry;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.CollectiveEntryRepository;
import com.ufpr.es.divresidapi.service.CollectiveEntryService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Service
public class CollectiveEntryServiceImpl 
	extends BaseResourceServiceImpl<CollectiveEntry, CollectiveEntryDTO, Long> 
	implements CollectiveEntryService {
	
	@Autowired
	private CollectiveEntryConverter collectiveConverter;
	@Autowired
	private CollectiveEntryRepository collectiveRepository;

	@Override
	public List<CollectiveEntryDTO> findAllByUser(User user) 
			throws ServiceException {
		// AInda não impl
		return null;
	}

	@Override
	protected ResourceConverter<CollectiveEntry, CollectiveEntryDTO> 
			getConverter() {
		return this.collectiveConverter;
	}

	@Override
	protected JpaRepository<CollectiveEntry, Long> getRepository() {
		return this.collectiveRepository;
	}

	@Override
	public CollectiveEntry saveModel(CollectiveEntry model) 
			throws ServiceException {
		return this.collectiveRepository.save(model);
	}

}
