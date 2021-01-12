package com.ufpr.es.divresidapi.service.impl;

import java.util.ArrayList;
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
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableWithDateFilterService;

@Service
public class CollectiveEntryServiceImpl 
	extends BaseResourceServiceImpl<CollectiveEntry, CollectiveEntryDTO, Long> 
	implements CollectiveEntryService, LazyTableWithDateFilterService<CollectiveEntry, User> {
	
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

	@Override
	public List<CollectiveEntryDTO> findAllByUserAndMonthAndYear
		(Long user, Integer month, Integer year)
			throws ServiceException {
		List<CollectiveEntryDTO> dtos = new ArrayList<CollectiveEntryDTO>();
		List<CollectiveEntry> models = new ArrayList<CollectiveEntry>();
		models = this.collectiveRepository
				.findAllByUserAndMonthAndYear(user, month, year);
		models.forEach(m ->{
			dtos.add(this.collectiveConverter.convertToDTO(m));
		});
		return dtos;
	}

	@Override
	public Page<CollectiveEntry> listAllPageableByMonthAndYearAndUser
		(Pageable pageable, Integer month, Integer year,Long user)
				throws ServiceException {
		return this.collectiveRepository
				.findAllByUserAndMonthAndYear(user, month, year, pageable);
	}


}
