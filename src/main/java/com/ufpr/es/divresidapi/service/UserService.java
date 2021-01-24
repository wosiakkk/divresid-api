package com.ufpr.es.divresidapi.service;

import org.springframework.stereotype.Repository;

import com.ufpr.es.divresidapi.dto.UserDTO;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Repository
public interface UserService extends BaseResourceService<UserDTO, Long>{
	UserDTO findUserByEmail(String email) throws ServiceException;
	UserDTO setNewRole(String roleName, Long userId) throws ServiceException;
	UserDTO setNewPass(String currentPass, String newpass, Long userId) 
														throws ServiceException;
}
	