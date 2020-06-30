package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.EntryDTO;
import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.EntryService;
import com.ufpr.es.divresidapi.service.LazyTableService;

@RestController
@RequestMapping(value = "api/auth/entries")
public class EntryController  extends BaseRestController<Entry, EntryDTO, Long>{

	@Autowired
	private EntryService entryService;
	@Autowired
	private LazyTableService<Entry> lazyTableService;
	
	@Override
	protected BaseResourceService<EntryDTO, Long> getBaseResourceService() {
		return this.entryService;
	}

	@Override
	protected LazyTableService<Entry> getLazyTableService() {
		return this.lazyTableService;
	}

}
