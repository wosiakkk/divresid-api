package com.ufpr.es.divresidapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.GoalDTO;
import com.ufpr.es.divresidapi.model.Goal;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.GoalService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;

@RestController
@RequestMapping("api/auth/goals")
public class GoalController
	extends BaseRestController<Goal, GoalDTO, User, Long>{
	
	@Autowired
	private GoalService goalService;
	@Autowired
	private LazyTableServiceByUser<Goal, User> lazyTableService;

	@Override
	protected BaseResourceService<GoalDTO, Long> getBaseResourceService() {
		return this.goalService;
	}

	@Override
	protected LazyTableServiceByUser<Goal, User> getLazyTableService() {
		return this.lazyTableService;
	}

	@GetMapping(value = "/active")
	@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<GoalDTO>> getAllActive(
			@RequestParam() Long user){
		try {
			return ResponseEntity.ok(this.goalService.getAllActive(user));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
