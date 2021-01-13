package com.ufpr.es.divresidapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.converter.UserConverter;
import com.ufpr.es.divresidapi.dto.UserDTO;
import com.ufpr.es.divresidapi.model.ERole;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.Role;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.RoleRepository;
import com.ufpr.es.divresidapi.repository.UserRepository;
import com.ufpr.es.divresidapi.service.UserService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;

@Service
public class UserServiceImpl 
	extends BaseResourceServiceImpl<User, UserDTO, Long>
	implements UserService, LazyTableServiceByUser<User, Property>{

	@Autowired
	private UserConverter userConverter;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDTO findUserByEmail(String email) throws ServiceException {
		return this.userConverter
				.convertToDTO(this.userRepository.findByEmail(email).get());
	}

	@Override
	protected ResourceConverter<User, UserDTO> getConverter() {
		return this.userConverter;
	}

	@Override
	protected JpaRepository<User, Long> getRepository() {
		return this.userRepository;
	}

	/*!!!!!!!!!!*/
	@Override
	public List<UserDTO> findAllByUser(User user) throws ServiceException {
		/*Problema de modelagem de interface, método sem utilidade, refatorar*/
		return null;
	}

	@Override
	public Page<User> listAllPageableAndUser(Pageable pageable, User user) 
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAllByNameContainingAndUser(String searchString,
			User user, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getNumberOfEntities(Property property) throws ServiceException {
		return null;//this.userRepository.countByProperty(property);
	}

	@Override
	public UserDTO setNewRole(String roleName, Long userId) 
			throws ServiceException {
		Role newRole =  new Role();
		if(roleName.equalsIgnoreCase("resident") ||
				roleName.equalsIgnoreCase("ROLE_RESIDENT"))
			newRole = this.roleRepository
				.findByName(ERole.ROLE_RESIDENT).get();
		else if(roleName.equalsIgnoreCase("moderator") ||
				roleName.equalsIgnoreCase("ROLE_MODERATOR"))
			newRole = this.roleRepository
				.findByName(ERole.ROLE_MODERATOR).get();
		else
			newRole = this.roleRepository
				.findByName(ERole.ROLE_ADMIN).get();
		
		User u =  this.userRepository.findById(userId).get();
		u.getRoles().clear();
		u.getRoles().add(newRole);
		
		UserDTO dto = this.userConverter
				.convertToDTO(this.userRepository.save(u));
		//UserDTO dto = this.update(this.userConverter.convertToDTO(u));
		
		return dto;
	}

}
