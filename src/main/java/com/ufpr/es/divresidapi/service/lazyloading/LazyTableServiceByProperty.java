package com.ufpr.es.divresidapi.service.lazyloading;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Component
public interface LazyTableServiceByProperty <TENTITY, TPROPERTY> {
	
	Page<TENTITY> listAllPageableAndProperty(Pageable pageable, 
			Property property) throws ServiceException;
	Page<TENTITY> findAllByNameContainingAndProperty(String searchString,
			Property property, Pageable pageable);
	Long getNumberOfEntities(TPROPERTY t) throws ServiceException;

}
