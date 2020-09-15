package com.ufpr.es.divresidapi.controller;

import java.util.NoSuchElementException;

import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.UserDTO;
import com.ufpr.es.divresidapi.model.ERole;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.Role;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.UserService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;

@RestController
@RequestMapping(value = "api/auth/residents")
public class UserController 
	extends BaseRestController<User, UserDTO,Property, Long>{

	@Autowired
	private UserService userService;
	@Autowired
	private LazyTableService<User, Property> lazyTableService;
	
	@Override
	protected BaseResourceService<UserDTO, Long> getBaseResourceService() {
		return this.userService;
	}

	@Override
	protected LazyTableService<User,Property> getLazyTableService() {
		return this.lazyTableService;
	}
	
	@GetMapping(value = "/byEmail")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<UserDTO> findByEmail(@RequestParam() String email)
			throws ServiceException{
		try {
			return ResponseEntity.ok(this.userService.findUserByEmail(email));
		}catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/newRole")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> setNewUserRole(@RequestBody User user) 
			throws ServiceException{
		String roleName ="";
		for (Role er : user.getRoles()) {
			if(er.getName().toString()!=null)
				roleName = er.getName().toString();
		}
		
		return ResponseEntity
				.ok(this.userService.setNewRole(roleName, user.getId()));
	}

}
