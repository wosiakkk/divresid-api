package com.ufpr.es.divresidapi.converter;

import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.dto.PropertyDTO;
import com.ufpr.es.divresidapi.model.Property;

@Component
public class PropertyConverter implements 
	ResourceConverter<Property, PropertyDTO>{

	@Override
	public Property convertToModel(PropertyDTO dto) {
		return new Property(dto.getId(), dto.getName(),dto.getDescription(),
				dto.getLandLordName(), dto.getLandLordPhone(), dto.getZipCode(),
				dto.getStreet(), dto.getAddressDetails(), dto.getNumber(),
				dto.getCity(), dto.getState(), dto.getUser(), dto.getResidents(),
				dto.isActive(), dto.getRules());
	}

	@Override
	public PropertyDTO convertToDTO(Property model) {
		return new PropertyDTO(model.getId(), model.getName(), 
				model.getDescription(), model.getLandLordName(), 
				model.getLandLordPhone(), model.getZipCode(), model.getStreet(),
				model.getAddressDetails(), model.getNumber(), model.getCity(),
				model.getState(), model.getUser(), model.getResidents(),
				model.isActive(), model.getRules());
	}

}
