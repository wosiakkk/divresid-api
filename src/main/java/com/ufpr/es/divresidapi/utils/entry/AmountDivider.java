
package com.ufpr.es.divresidapi.utils.entry;

import java.util.ArrayList;
import java.util.List;

import com.ufpr.es.divresidapi.dto.CollectiveEntryDTO;
import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;

public class AmountDivider {
	
	public List<Entry> prepare(CollectiveEntryDTO dto) {
		float valor = divide(dto.getAmount(), dto.getResidents().size());
		return entriesFactory(valor, dto);
	}
	
	private float divide(float value, int numberOfPersons) {
		return value / numberOfPersons;
	}
	
	private List<Entry> entriesFactory(float individualValue,
			CollectiveEntryDTO dto ){
		List<Entry> entries =  new ArrayList<Entry>();
		for (User user : dto.getResidents()) {
			Entry e = new Entry();
			e.setName(dto.getName());
			e.setDescription(dto.getDescription());
			e.setType(dto.getType());
			e.setAmount(individualValue);
			e.setDate(dto.getDate());
			e.setPaid(false);
			e.setCategory(dto.getCategory());
			e.setUser(user);
			e.setCollective(true);
			entries.add(e);
		}
		return entries;
	}
	
}
