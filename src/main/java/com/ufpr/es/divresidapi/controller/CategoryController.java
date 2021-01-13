package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.CategoryDTO;
import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.CategoryService;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByProperty;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;


@RestController
@RequestMapping("api/auth/categories")
public class CategoryController 
	extends BaseRestController<Category, CategoryDTO,User, Long>{

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private LazyTableServiceByUser<Category, User> lazyTableService;
	
	@Override
	protected BaseResourceService<CategoryDTO, Long> getBaseResourceService() {
		return this.categoryService;
	}

	@Override
	protected LazyTableServiceByUser<Category,User> getLazyTableService() {
		return this.lazyTableService;
	}


}
