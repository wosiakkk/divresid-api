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
								   dto.getProperty(), dto.getUser());
	}

	@Override
	public CollectiveEntryDTO convertToDTO(CollectiveEntry model) {
		return new CollectiveEntryDTO(model.getId(), model.getAmount(),
				model.getProperty(), model.getUser());
	}

}
