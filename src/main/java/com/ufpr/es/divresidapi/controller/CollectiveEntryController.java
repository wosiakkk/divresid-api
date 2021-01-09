package com.ufpr.es.divresidapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.converter.CollectiveEntryConverter;
import com.ufpr.es.divresidapi.converter.EntryConverter;
import com.ufpr.es.divresidapi.dto.CollectiveEntryDTO;
import com.ufpr.es.divresidapi.dto.EntryDTO;
import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.model.CollectiveEntry;
import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.repository.CategoryRepository;
import com.ufpr.es.divresidapi.repository.CollectiveEntryRepository;
import com.ufpr.es.divresidapi.repository.EntryRepository;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.CollectiveEntryService;
import com.ufpr.es.divresidapi.service.EntryService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;
import com.ufpr.es.divresidapi.utils.entry.AmountDividerController;

@RestController
@RequestMapping("api/auth/collective")
public class CollectiveEntryController 
	extends BaseRestController<CollectiveEntry, CollectiveEntryDTO, User, Long> {

	@Autowired
	private CollectiveEntryService collectiveService;
	@Autowired
	private EntryService entryService;
	@Autowired
	private LazyTableService<CollectiveEntry, User> lazyTableService;
	@Autowired
	private CollectiveEntryConverter collectiveConverter;
	@Autowired
	private EntryConverter entryConverter;
	@Autowired
	private CollectiveEntryRepository collectiveRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private EntryRepository entryRepository;
	@Autowired
	private AmountDividerController amountDividerController;

	@Override
	protected BaseResourceService<CollectiveEntryDTO, Long> 
													getBaseResourceService() {
		return this.collectiveService;
	}

	@Override
	protected LazyTableService<CollectiveEntry, User> getLazyTableService() {
		return this.lazyTableService;
	}

	@Override
	@PostMapping
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectiveEntryDTO> save(
										@RequestBody CollectiveEntryDTO dto) {
		try {

			CollectiveEntry model = this.collectiveConverter
					.convertToModel(dto);
			List<Entry> entries = amountDividerController.prepare(dto);
			List<Entry> persistedEntries = this.entryService.saveAll(entries);
			model.setGeneratedEntries(persistedEntries);
			CollectiveEntry persistedModel = this.collectiveService
															.saveModel(model);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(this.collectiveConverter
												.convertToDTO(persistedModel));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<CollectiveEntryDTO> 
								update(@RequestBody CollectiveEntryDTO dto) {
		try {
			return ResponseEntity
						.ok(prepareAndExecuteActionsInEntriesForUpdate(dto));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Transactional
	private CollectiveEntryDTO prepareAndExecuteActionsInEntriesForUpdate
		(CollectiveEntryDTO dto) throws ServiceException {
		
		CollectiveEntry model = this.collectiveConverter
				.convertToModel(dto);
		CollectiveEntry beforeUpdate = this.collectiveRepository
				.findById(dto.getId()).get();
		Category category = categoryRepository
				.findById(dto.getCategoryId()).get();
		dto.setCategory(category);
		
		float newAmountValue = amountDividerController
				.divide(dto.getAmount(), dto.getSelectedResidents().size());
		
		runDeleteEntriesForUpdateColl(beforeUpdate, dto);
		
		List<Entry> entriesUp = 
				runUpdateEntriesForUpdateColl(beforeUpdate, dto, newAmountValue);
		
		List<Entry> entriesCr =
				runCreateEntriesForUpdateColl(beforeUpdate, dto, newAmountValue);
		
		if(entriesCr != null && entriesUp != null) {
			entriesCr.addAll(entriesUp);
			model.setGeneratedEntries(entriesCr);
		} else if(entriesCr!= null)
			model.setGeneratedEntries(entriesCr);
		else if(entriesUp != null)
			model.setGeneratedEntries(entriesUp);
		
		model.setCategory(category);
		
		return collectiveConverter.convertToDTO(collectiveService.saveModel(model));
	}
	

	private void runDeleteEntriesForUpdateColl(CollectiveEntry beforeUpdate,
			CollectiveEntryDTO dto) throws ServiceException {
		List<Entry> entriesToDelete = amountDividerController
				.checkEntriesToDelete(
						beforeUpdate.getGeneratedEntries(),
						dto.getSelectedResidents()
				);
		for (Entry entry : entriesToDelete) {
			collectiveRepository
				.deleteGeneratedEntryAssoc(dto.getId(), entry.getId());
			this.entryRepository.deleteById(entry.getId());
		}
	}
	
	private List<Entry> runUpdateEntriesForUpdateColl(
			CollectiveEntry beforeUpdate, CollectiveEntryDTO dto,
			float newValue) throws ServiceException {
		List<Entry> entriesToUpdate = amountDividerController
				.checkEntriesToUpdate(
						beforeUpdate.getGeneratedEntries(), 
						dto.getSelectedResidents()
				);
		List<Entry> updatedEntries = new ArrayList<Entry>();
		for (Entry entry : entriesToUpdate) {
			entry.setName(dto.getName());
			entry.setDescription(dto.getDescription());
			entry.setType(dto.getType());
			entry.setAmount(newValue);
			entry.setDate(dto.getDate());
			entry.setPaid(false);
			entry.setCategory(dto.getCategory());
			//entry.setUser(user);
			entry.setCollective(true);
			EntryDTO newEntryDto = this.entryConverter.convertToDTO(entry);
			updatedEntries.add(
					entryConverter
						.convertToModel(this.entryService.update(newEntryDto))
			);
		}
		if(updatedEntries.isEmpty())
			return null;
		else
			return updatedEntries;
	}
	
	private List<Entry> runCreateEntriesForUpdateColl(
			CollectiveEntry beforeUpdate, CollectiveEntryDTO dto, 
			float newValue) throws ServiceException {
		List<User> usersToCreateNewEntries = amountDividerController
				.checkEntriesToCreateForUsers(
						beforeUpdate.getGeneratedEntries(), 
						dto.getSelectedResidents()
				);
		List<Entry> newsEntries = new ArrayList<Entry>();
		for (User user : usersToCreateNewEntries) {
			Entry entry = amountDividerController
					.entryFactory(newValue, dto, user);
			newsEntries.add(entry);
		}
		if(newsEntries.isEmpty())
			return null;
		else
			return this.entryService.saveAll(newsEntries);
	}

}
