package com.ufpr.es.divresidapi.converter;

import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.dto.CollectiveEntryDTO;
import com.ufpr.es.divresidapi.model.CollectiveEntry;

@Component
public class CollectiveEntryConverter 
	implements ResourceConverter<CollectiveEntry, CollectiveEntryDTO>{

	@Override
	public CollectiveEntry convertToModel(CollectiveEntryDTO dto) {
		return new CollectiveEntry(dto.getId(), dto.getAmount(),
				dto.getProperty(),dto.getUser(),dto.getDate(),
				dto.getDescription(),dto.getName(),dto.getType(),
				dto.getCategory());
	}

	@Override
	public CollectiveEntryDTO convertToDTO(CollectiveEntry model) {
		return new CollectiveEntryDTO(model.getId(), model.getName(), 
				model.getDescription(), model.getType(), model.getAmount(), 
				model.getDate(), model.getCategory(), model.getProperty(), 
				model.getUser());
	}

}
