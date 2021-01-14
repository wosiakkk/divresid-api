package com.ufpr.es.divresidapi.service.lazyloading;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Component
public interface LazyTableServiceByUser<TENTITY, USER> {
	
	Page<TENTITY> listAllPageableAndUser(Pageable pageable, 
			User user)throws ServiceException;
	Page<TENTITY> findAllByNameContainingAndUser(String searchString,
			User user, Pageable pageable);
	Long getNumberOfEntities(USER t) throws ServiceException;
	
}
