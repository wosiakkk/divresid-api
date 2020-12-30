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
import com.ufpr.es.divresidapi.model.CollectiveEntry;
import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.CollectiveEntryService;
import com.ufpr.es.divresidapi.service.EntryService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;
import com.ufpr.es.divresidapi.utils.entry.AmountDivider;

@RestController
@RequestMapping("api/auth/collective")
public class CollectiveEntryController 
	extends BaseRestController<CollectiveEntry, CollectiveEntryDTO, User, Long>{

	@Autowired
	private CollectiveEntryService collectiveService;
	@Autowired
	private EntryService entryService;
	@Autowired
	private CollectiveEntryConverter collectiveConverter;
	
	
	@Override
	protected BaseResourceService<CollectiveEntryDTO, Long> 
		getBaseResourceService() {
		return this.collectiveService;
	}

	@Override
	protected LazyTableService<CollectiveEntry, User> getLazyTableService() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@PostMapping
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<CollectiveEntryDTO> 
									save(@RequestBody CollectiveEntryDTO dto) {
		try {
			
			CollectiveEntry model = this.collectiveConverter.convertToModel(dto);
			AmountDivider ad =  new AmountDivider();
			List<Entry> entries = ad.prepare(dto);
			List<Entry> persistedEntries =  this.entryService.saveAll(entries);
			model.setGeneratedEntries(persistedEntries);
			CollectiveEntry persistedModel = this.collectiveService
														.saveModel(model);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(this.collectiveConverter.convertToDTO(persistedModel));
		}catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * 
	 * 	@PostMapping(value = "/collective")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@Transactional
	public ResponseEntity<List<EntryDTO>> 
		generateCollectiveEntries(@RequestBody CollectiveEntryDTO dto){
		try {
			AmountDivider ad =  new AmountDivider();
			List<Entry> entries = ad.prepare(dto);
			List<Entry> persistedEntries =  this.entryService.saveAll(entries);
			List<EntryDTO> dtos = new ArrayList<>();
			persistedEntries
				.forEach(e -> dtos.add(this.entryConverter.convertToDTO(e)));

			return ResponseEntity.status(HttpStatus.CREATED).body(dtos);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 * */
}
