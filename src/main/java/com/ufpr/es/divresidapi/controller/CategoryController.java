package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.CategoryDTO;
import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/auth/categories")
public class CategoryController extends BaseRestController<Category, CategoryDTO, Long>{

	@Autowired
	private CategoryService categoryService;
	
	@Override
	protected BaseResourceService<Category,CategoryDTO, Long> getBaseResourceService() {
		return this.categoryService;
	}

}
