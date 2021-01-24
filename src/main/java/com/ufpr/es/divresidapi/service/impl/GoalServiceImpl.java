package com.ufpr.es.divresidapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.GoalConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.dto.GoalDTO;
import com.ufpr.es.divresidapi.model.Goal;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.GoalRepository;
import com.ufpr.es.divresidapi.service.GoalService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;

@Service
public class GoalServiceImpl 
	extends BaseResourceServiceImpl<Goal, GoalDTO, Long>
	implements GoalService,
				LazyTableServiceByUser<Goal, User>{
	
	@Autowired
	private GoalConverter goalConverter;
	@Autowired
	private GoalRepository goalRepository;

	@Override
	public List<GoalDTO> findAllByUser(User user) throws ServiceException {
		List<GoalDTO> dtos = new ArrayList<>();
		List<Goal> models = this.goalRepository.findAllByUser(user);
		models.forEach(
			model ->
				dtos.add(this.goalConverter.convertToDTO(model))
		);
		return dtos;
	}

	@Override
	protected ResourceConverter<Goal, GoalDTO> getConverter() {
		return this.goalConverter;
	}

	@Override
	protected JpaRepository<Goal, Long> getRepository() {
		return this.goalRepository;
	}

	@Override
	public Page<Goal> listAllPageableAndUser(Pageable pageable, User user)
			throws ServiceException {
		return goalRepository.findAllByUser(pageable, user);
	}

	@Override
	public Page<Goal> findAllByNameContainingAndUser(String searchString,
			User user, Pageable pageable) {
		return this.goalRepository
				.findAllByNameContainingAndUser(searchString, user, pageable);
	}

	@Override
	public Long getNumberOfEntities(User t) throws ServiceException {
		return this.goalRepository.countByUser(t);
	}

	@Override
	public List<GoalDTO> getAllActive(Long userId) throws ServiceException {
		User user = new User();
		user.setId(userId);
		List<Goal> models =
				this.goalRepository.findAllByUserAndDone(user, false);
		List<GoalDTO> dtos = new ArrayList<>();
		models.forEach(m ->
			dtos.add(this.goalConverter.convertToDTO(m))
		);
		return dtos;
	}

}
