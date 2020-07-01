package com.ufpr.es.divresidapi.service;

import java.util.List;

import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface BaseResourceService<TDTO, TID> {
	
	TDTO save(TDTO dto) throws ServiceException;
	void delete(TID id) throws ServiceException; 
	TDTO update(TDTO dto) throws ServiceException; 
	List<TDTO> findAll() throws ServiceException;
	List<TDTO> findAllByUser(User user) throws ServiceException;
	TDTO findById(TID id) throws ServiceException;
	
}
