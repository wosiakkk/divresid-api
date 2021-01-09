
package com.ufpr.es.divresidapi.utils.entry;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ufpr.es.divresidapi.dto.CollectiveEntryDTO;
import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.model.User;

@Component
public class AmountDividerController {
	
	public List<Entry> prepare(CollectiveEntryDTO dto) {
		float valor = divide(dto.getAmount(), dto.getResidents().size());
		return entriesFactory(valor, dto);
	}
	
	public float divide(float value, int numberOfPersons) {
		return value / numberOfPersons;
	}
	
	private List<Entry> entriesFactory(
			float individualValue,
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
	
	public Entry entryFactory(float individualValue, 
			CollectiveEntryDTO dto, User user) {
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
		return e;
	}
	
	public List<Entry> checkEntriesToDelete(
			List<Entry> oldGenCollEntites, 
			List<User> newSelectedResidents) {
		
		List<Entry> entriesToDelete = new ArrayList<Entry>();
		
		for (Entry entry : oldGenCollEntites) {
			boolean mustDelete = true;
			for (User userSelected : newSelectedResidents) {
				if(entry.getUser().getId() == userSelected.getId())
					mustDelete= false;
			}
			if(mustDelete)
				entriesToDelete.add(entry);
		}
		return entriesToDelete;
	}
	
	public List<Entry> checkEntriesToUpdate(
			List<Entry> oldGenCollEntites, 
			List<User> newSelectedResidents) {
		
		List<Entry> entriesToUpdate = new ArrayList<Entry>();
		
		for (Entry entry : oldGenCollEntites) {
			boolean mustUpdate = false;
			for (User userSelected : newSelectedResidents) {
				if(entry.getUser().getId() == userSelected.getId())
					mustUpdate= true;
			}
			if(mustUpdate)
				entriesToUpdate.add(entry);
		}
		return entriesToUpdate;
	}
	
	public List<User> checkEntriesToCreateForUsers(
			List<Entry> oldGenCollEntites, 
			List<User> newSelectedResidents) {
		
		List<User> usersToCreateEntries = new ArrayList<User>();
		
		for (User user : newSelectedResidents) {
			boolean mustCreate = true;
			for (Entry oldEntries : oldGenCollEntites) {
				if(user.getId() == oldEntries.getUser().getId())
					mustCreate = false;
			}
			if(mustCreate)
				usersToCreateEntries.add(user);
		}
		
		return usersToCreateEntries;
	}
	
}
