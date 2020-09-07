package com.ufpr.es.divresidapi.converter;

import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.dto.UserDTO;
import com.ufpr.es.divresidapi.model.User;

@Component
public class UserConverter implements ResourceConverter<User, UserDTO>{

	@Override
	public User convertToModel(UserDTO dto) {
		return new User(dto.getId(), dto.getUsername(), 
				dto.getEmail(), dto.getName());
	}

	@Override
	public UserDTO convertToDTO(User model) {
		return new UserDTO(model.getId(), model.getUsername(),
				model.getEmail(), model.getName());
	}

}
