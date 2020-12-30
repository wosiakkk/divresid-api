package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.CollectiveEntryDTO;
import com.ufpr.es.divresidapi.model.CollectiveEntry;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.CollectiveEntryService;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;

@RestController
@RequestMapping("api/auth/collectives")
public class CollectiveEntryController 
	extends BaseRestController<CollectiveEntry, CollectiveEntryDTO, User, Long>{

	@Autowired
	private CollectiveEntryService collectiveService;
	
	
	@Override
	protected BaseResourceService<CollectiveEntryDTO, Long> getBaseResourceService() {
		return this.collectiveService;
	}

	@Override
	protected LazyTableService<CollectiveEntry, User> getLazyTableService() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
