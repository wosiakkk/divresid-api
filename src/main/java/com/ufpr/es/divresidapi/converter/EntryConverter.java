package com.ufpr.es.divresidapi.converter;

import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.dto.EntryDTO;
import com.ufpr.es.divresidapi.model.Entry;

@Component
public class EntryConverter implements ResourceConverter<Entry, EntryDTO> {

	@Override
	public Entry convertToModel(EntryDTO dto) {
		return new Entry(dto.getId(), dto.getName(), dto.getDescription(),
				dto.getType(), dto.getAmount(), dto.getDate(), dto.isPaid(),
				dto.getCategory(), dto.getUser(), dto.isCollective());
	}

	@Override
	public EntryDTO convertToDTO(Entry model) {
		return new EntryDTO(model.getId(), model.getName(),
				model.getDescription(), model.getType(), model.getAmount(),
				model.getDate(), model.isPaid(), model.getCategory(),
				model.getUser(), model.isCollective());
	}

}
