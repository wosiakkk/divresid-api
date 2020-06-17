package com.ufpr.es.divresidapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.CategoryConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.dto.CategoryDTO;
import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.repository.CategoryRepository;
import com.ufpr.es.divresidapi.service.CategoryService;

@Service
public class CategoryServiceImpl extends BaseResourceServiceImpl<CategoryDTO, Category, Long> implements CategoryService {
	
	@Autowired
	private CategoryConverter categoryConverter;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	protected ResourceConverter<Category, CategoryDTO> getConverter() {
		return this.categoryConverter;
	}

	@Override
	protected CrudRepository<Category, Long> getRepository() {
		return this.categoryRepository;
	}

}