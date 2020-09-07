package com.ufpr.es.divresidapi.controller;

import com.ufpr.es.divresidapi.dto.UserDTO;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;

public class UserController 
	extends BaseRestController<User, UserDTO,Property, Long>{

	@Override
	protected BaseResourceService<UserDTO, Long> getBaseResourceService() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected LazyTableService<User,Property> getLazyTableService() {
		// TODO Auto-generated method stub
		return null;
	}

}
