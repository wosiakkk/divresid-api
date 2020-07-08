package com.ufpr.es.divresidapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.ufpr.es.divresidapi.converter.CategoryConverter;
import com.ufpr.es.divresidapi.converter.ResourceConverter;
import com.ufpr.es.divresidapi.dto.CategoryDTO;
import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.CategoryRepository;
import com.ufpr.es.divresidapi.service.CategoryService;
import com.ufpr.es.divresidapi.service.LazyTableService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;

@Service
public class CategoryServiceImpl 
	extends BaseResourceServiceImpl<Category, CategoryDTO, Long> 
	implements CategoryService, LazyTableService<Category> {
	
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
	public Page<Category> listAllPageableAndUser(Pageable pageable, User user) 
			throws ServiceException {
		return categoryRepository.findAllByUser(pageable,user);
	}
	
	@Override
	public Page<Category> findAllByNameContainingAndUser(
			String name,User user, Pageable pageable
		) {
		return categoryRepository
						.findAllByNameContainingAndUser(name,user, pageable);
	}

	@Override
	public Long getNumberOfEntities(User user) throws ServiceException {
		return categoryRepository.countByUser(user);
	}

	@Override
	public List<CategoryDTO> findAllByUser(User user) throws ServiceException {
		List<CategoryDTO> dtos = new ArrayList<>();
		List<Category> models = this.categoryRepository.findAllByUser(user);
		models.forEach(
			model -> 
			dtos.add(this.categoryConverter.convertToDTO(model))
		);
		return dtos;
	}

	@Override
	public Page<Category> listAllPageableByMonthAndYearAndUser(Pageable pageable, Integer month, Integer year,
			Long user) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
