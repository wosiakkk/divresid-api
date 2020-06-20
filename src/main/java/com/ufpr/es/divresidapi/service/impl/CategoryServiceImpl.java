package com.ufpr.es.divresidapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.CategoryConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.dto.CategoryDTO;
import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.repository.CategoryRepository;
import com.ufpr.es.divresidapi.service.CategoryService;
import com.ufpr.es.divresidapi.service.LazyTableService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Service
public class CategoryServiceImpl extends BaseResourceServiceImpl<Category, CategoryDTO, Long> implements CategoryService, LazyTableService<Category> {
	
	@Autowired
	private CategoryConverter categoryConverter;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	protected ResourceConverter<Category, CategoryDTO> getConverter() {
		return this.categoryConverter;
	}

	@Override
	protected JpaRepository<Category, Long> getRepository() {
		return this.categoryRepository;
	}

	@Override
	public Page<Category> listAllPageable(Pageable pageable) throws ServiceException {
		return categoryRepository.findAll(pageable);
	}
	
	@Override
	public Page<Category> findAllByNameContaining(String name, Pageable pageable) {
		return categoryRepository.findAllByNameContaining(name, pageable);
	}

	@Override
	public Long getNumberOfEntities() throws ServiceException {
		return categoryRepository.count();
	}
}
