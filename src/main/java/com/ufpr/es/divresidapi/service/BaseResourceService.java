package com.ufpr.es.divresidapi.service;

import java.util.List;

import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface BaseResourceService<TDTO, TID> {
	
	TDTO save(TDTO dto) throws ServiceException;
	void delete(TID id) throws ServiceException; 
	TDTO update(TDTO dto) throws ServiceException; 
	List<TDTO> listAll() throws ServiceException;
	TDTO findById(TID id) throws ServiceException;
	
}
