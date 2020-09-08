package com.ufpr.es.divresidapi.service.lazyloading;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface LazyTableService<TENTITY,TENTITYCOUNT> {
	
	Page<TENTITY> listAllPageableAndUser(Pageable pageable, 
			User user)throws ServiceException;
	Page<TENTITY> findAllByNameContainingAndUser(String searchString,
			User user, Pageable pageable);
	Long getNumberOfEntities(TENTITYCOUNT t) throws ServiceException;
	
}
