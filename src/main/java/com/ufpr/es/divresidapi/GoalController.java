package com.ufpr.es.divresidapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.controller.BaseRestController;
import com.ufpr.es.divresidapi.dto.GoalDTO;
import com.ufpr.es.divresidapi.model.Goal;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.GoalService;
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

}
