package com.ufpr.es.divresidapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.EntryConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.dto.EntryDTO;
import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.EntryRepository;
import com.ufpr.es.divresidapi.service.EntryService;
import com.ufpr.es.divresidapi.service.LazyTableService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Service
public class EntryServiceImpl 
	extends BaseResourceServiceImpl<Entry, EntryDTO, Long> 
	implements EntryService, LazyTableService<Entry>{
	
	@Autowired
	private EntryRepository entryRepository;
	@Autowired
	private EntryConverter entryConverter;

	@Override
	public Page<Entry> listAllPageableAndUser(Pageable pageable, User user) 
			throws ServiceException {
		return this.entryRepository.findAllByUser(pageable, user);
	}

	@Override
	public Page<Entry> findAllByNameContainingAndUser(String name, User user, 
			Pageable pageable) {
		return this.findAllByNameContainingAndUser(name, user, pageable);
	}

	@Override
	public Long getNumberOfEntities(User user) throws ServiceException {
		return this.entryRepository.countByUser(user);
	}

	@Override
	protected ResourceConverter<Entry, EntryDTO> getConverter() {
		return this.entryConverter;
	}

	@Override
	protected JpaRepository<Entry, Long> getRepository() {
		return this.entryRepository;
	}

	@Override
	public List<EntryDTO> findAllByUser(User user) throws ServiceException {
		List<EntryDTO> dtos = new ArrayList<>();
		List<Entry> model =this.entryRepository.findAllByUser(user);
		model.forEach(entry -> dtos.add(this.getConverter().convertToDTO(entry)));
		return dtos;
	}

}
