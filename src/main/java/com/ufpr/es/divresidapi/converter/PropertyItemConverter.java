package com.ufpr.es.divresidapi.converter;

import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.dto.PropertyItemDTO;
import com.ufpr.es.divresidapi.model.PropertyItem;

@Component
public class PropertyItemConverter 
	implements ResourceConverter<PropertyItem, PropertyItemDTO> {

	@Override
	public PropertyItem convertToModel(PropertyItemDTO dto) {
		return new PropertyItem(dto.getId(), dto.getName(), 
				dto.getDescription(), dto.getOwner(), 
				dto.getProperty(), dto.getUser(),dto.getImage());
	}

	@Override
	public PropertyItemDTO convertToDTO(PropertyItem model) {
		return new PropertyItemDTO(model.getId(), model.getName(), 
				model.getDescription(), model.getOwner(),
				model.getProperty(), model.getUser(), model.getImage());
	}

}
