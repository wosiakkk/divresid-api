package com.ufpr.es.divresidapi.service.lazyloading;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Component
public interface LazyTableWithDateFilterService<TENTITY,TENTITYCOUNT> 
	extends LazyTableServiceByUser<TENTITY,TENTITYCOUNT> {
	
	Page<TENTITY> listAllPageableByMonthAndYearAndUser(Pageable pageable,
			Integer month, Integer year, Long user)throws ServiceException;
}
