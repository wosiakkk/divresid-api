package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.CategoryDTO;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.CategoryService;

@RestController
@RequestMapping("api/auth/categories")
public class CategoryController extends BaseRestController<CategoryDTO, Long>{

	@Autowired
	private CategoryService categoryService;
	
	@Override
	protected BaseResourceService<CategoryDTO, Long> getBaseResourceService() {
		return this.categoryService;
	}

}
