package com.ufpr.es.divresidapi.service;

import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.dto.UserDTO;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Repository
public interface UserService extends BaseResourceService<UserDTO, Long>{
	UserDTO findUserByEmail(String email) throws ServiceException;
}
