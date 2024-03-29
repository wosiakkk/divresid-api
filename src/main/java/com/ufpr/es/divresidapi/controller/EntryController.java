package com.ufpr.es.divresidapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.converter.EntryConverter;
import com.ufpr.es.divresidapi.dto.CollectiveEntryDTO;
import com.ufpr.es.divresidapi.dto.EntryDTO;
import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.EntryService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableServiceByUser;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableWithDateFilterService;
import com.ufpr.es.divresidapi.utils.entry.AmountDividerController;

@RestController
@RequestMapping(value = "api/auth/entries")
public class EntryController  
	extends BaseRestController<Entry, EntryDTO,User, Long>{

	@Autowired
	private EntryService entryService;
	@Autowired
	private LazyTableWithDateFilterService<Entry,User> lazyTableService;
	@Autowired
	private EntryConverter entryConverter;
	
	@Override
	protected BaseResourceService<EntryDTO, Long> getBaseResourceService() {
		return this.entryService;
	}

	@Override
	protected LazyTableServiceByUser<Entry,User> getLazyTableService() {
		return this.lazyTableService;
	}
	
	@GetMapping(value = "/byDate")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<EntryDTO>> 
		findAllByUserAndMonthAndYear(
				@RequestParam() String userId,
				@RequestParam() String month,
				@RequestParam() String year){
		try {
			return ResponseEntity
					.ok(this.entryService
							.findAllByUserAndMonthAndYear(
									Long.valueOf(userId),
									Integer.valueOf(month),
									Integer.valueOf(year))
							);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/pagination/filtered")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Page<Entry>> 
		findAllByUserAndMonthAndYearPagination(
				Pageable pageable,
				@RequestParam() String userId,
				@RequestParam() String month,
				@RequestParam() String year){
		try {
			return ResponseEntity
					.ok(this.lazyTableService
							.listAllPageableByMonthAndYearAndUser(
									pageable,
									Integer.valueOf(month),
									Integer.valueOf(year),
									Long.valueOf(userId))
							);
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
