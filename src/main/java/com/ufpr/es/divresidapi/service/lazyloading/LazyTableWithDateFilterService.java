package com.ufpr.es.divresidapi.service.lazyloading;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ufpr.es.divresidapi.service.exception.ServiceException;

public interface LazyTableWithDateFilterService<TENTITY> 
	extends LazyTableService<TENTITY> {
	
	Page<TENTITY> listAllPageableByMonthAndYearAndUser(Pageable pageable,
			Integer month, Integer year, Long user)throws ServiceException;
}