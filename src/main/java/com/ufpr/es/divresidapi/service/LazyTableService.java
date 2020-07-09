package com.ufpr.es.divresidapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface LazyTableService<TENTITY> {
	
	Page<TENTITY> listAllPageableAndUser(Pageable pageable, User user)throws ServiceException;
	Page<TENTITY> listAllPageableByMonthAndYearAndUser(Pageable pageable,Integer month, Integer year, Long user)throws ServiceException;
	Page<TENTITY> findAllByNameContainingAndUser(String searchString,User user, Pageable pageable);
	Long getNumberOfEntities(User user) throws ServiceException;
	
}
