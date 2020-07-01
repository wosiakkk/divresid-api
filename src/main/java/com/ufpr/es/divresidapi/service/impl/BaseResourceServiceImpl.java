package com.ufpr.es.divresidapi.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;


public abstract class BaseResourceServiceImpl<TENTITY, TDTO, TID> implements BaseResourceService<TDTO, TID> {

	protected abstract ResourceConverter<TENTITY, TDTO> getConverter();
	protected abstract JpaRepository<TENTITY,TID> getRepository();
	
	
	@Override
	public List<TDTO> findAll() throws ServiceException {
		List<TDTO> dtos = new ArrayList<TDTO>();
		Iterable<TENTITY> elements = this.getRepository().findAll();
		elements.forEach(e -> dtos.add(this.getConverter().convertToDTO(e)));
		return dtos;
	}
	
	@Override
	public TDTO findById(TID id) throws ServiceException {
		TENTITY entity = this.getRepository()
				.findById(id).orElseThrow(() -> new ServiceException("Recurso não encontrado"));
		return getConverter().convertToDTO(entity);
	}
	
	@Override
	public TDTO save(TDTO dto) throws ServiceException {
		TENTITY entity = this.getConverter().convertToModel(dto);
		return this.getConverter().convertToDTO(this.getRepository().save(entity));
	}
	
	@Override
	public TDTO update(TDTO dto) throws ServiceException {
		TENTITY entity = this.getConverter().convertToModel(dto);
		return this.getConverter().convertToDTO(this.getRepository().save(entity));
	}
	
	@Override
	public void delete(TID id) throws ServiceException {
		getRepository().deleteById(id);
	}
	
}
