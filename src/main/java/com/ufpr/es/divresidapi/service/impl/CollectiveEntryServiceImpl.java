package com.ufpr.es.divresidapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;

@Service
public class CollectiveEntryServiceImpl 
	extends BaseResourceServiceImpl<CollectiveEntry, CollectiveEntryDTO, Long> 
	implements CollectiveEntryService, LazyTableService<CollectiveEntry, User> {
	
	@Autowired
	private CollectiveEntryConverter collectiveConverter;
	@Autowired
	private CollectiveEntryRepository collectiveRepository;

	
	@Override
	public CollectiveEntry saveModel(CollectiveEntry model) 
			throws ServiceException {
		return collectiveRepository.save(model);
	}
	
	@Override
	protected ResourceConverter<CollectiveEntry, CollectiveEntryDTO> 
			getConverter() {
		return collectiveConverter;
	}

	@Override
	protected JpaRepository<CollectiveEntry, Long> getRepository() {
		return collectiveRepository;
	}
	
	@Override
	public List<CollectiveEntryDTO> findAllByUser(User user) 
			throws ServiceException {
		return null;
	}
	
	@Override
	public Page<CollectiveEntry> listAllPageableAndUser(Pageable pageable,
			User user) throws ServiceException {
		return collectiveRepository.findAllByUser(pageable, user);
	}

	@Override
	public Page<CollectiveEntry> findAllByNameContainingAndUser(
			String searchString, User user, Pageable pageable) {
		return collectiveRepository
				.findAllByNameContainingAndUser(searchString, user, pageable);
	}

	@Override
	public Long getNumberOfEntities(User user) throws ServiceException {
		return collectiveRepository.countByUser(user);
	}


}
