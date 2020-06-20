package com.ufpr.es.divresidapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface LazyTableService<TENTITY> {
	
	Page<TENTITY> listAllPageable(Pageable pageable)throws ServiceException;
	Page<TENTITY> findAllByNameContaining(String name, Pageable pageable);
	Long getNumberOfEntities() throws ServiceException;
	
}
