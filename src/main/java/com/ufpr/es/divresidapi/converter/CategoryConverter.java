package com.ufpr.es.divresidapi.converter;

import com.ufpr.es.divresidapi.dto.CategoryDTO;
import com.ufpr.es.divresidapi.model.Category;

public class CategoryConverter implements ResourceConverter<Category, CategoryDTO> {

	@Override
	public Category convertToModel(CategoryDTO dto) {
		return new Category(dto.getId(), dto.getDescription());
	}

	@Override
	public CategoryDTO convertToDTO(Category model) {
		return new CategoryDTO(model.getId(), model.getDescription());
	}

}
