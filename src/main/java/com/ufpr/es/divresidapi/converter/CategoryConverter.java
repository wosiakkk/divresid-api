package com.ufpr.es.divresidapi.converter;

import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.dto.CategoryDTO;
import com.ufpr.es.divresidapi.model.Category;
@Component
public class CategoryConverter implements ResourceConverter<Category, CategoryDTO> {

	@Override
	public Category convertToModel(CategoryDTO dto) {
		return new Category(dto.getId(),dto.getName(), dto.getDescription(),dto.getUser());
	}

	@Override
	public CategoryDTO convertToDTO(Category model) {
		return new CategoryDTO(model.getId(), model.getName(), model.getDescription(), model.getUser());
	}

}
