package com.ufpr.es.divresidapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufpr.es.divresidapi.dto.InviteDTO;
import com.ufpr.es.divresidapi.model.Invite;
import com.ufpr.es.divresidapi.model.User;
import com.ufpr.es.divresidapi.service.BaseResourceService;
import com.ufpr.es.divresidapi.service.InviteService;
import com.ufpr.es.divresidapi.service.exception.ServiceException;
import com.ufpr.es.divresidapi.service.lazyloading.LazyTableService;

@RestController
@RequestMapping(value = "api/auth/invites")
public class InviteController 
	extends BaseRestController<Invite, InviteDTO, User, Long>{
	
	@Autowired
	private InviteService inviteService;
	@Autowired
	private LazyTableService<Invite, User> lazyTableService;
	
	@Override
	protected BaseResourceService<InviteDTO, Long> getBaseResourceService() {
		return this.inviteService;
	}

	@Override
	protected LazyTableService<Invite, User> getLazyTableService() {
		return this.lazyTableService;
	}
	
	@PostMapping(value = "/accept")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<InviteDTO> acceptInvite(@RequestBody Invite invite){
		try {
			return ResponseEntity
					.status(HttpStatus.HTTP_VERSION_NOT_SUPPORTED.CREATED)
					.body(this.inviteService.acceptInvite(invite));
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	@GetMapping(value = "/pagination")
	@PreAuthorize("hasRole('RESIDENT') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Page<Invite>> listAllPageable(Pageable pageable,
			String searchString, User user) {
		try {
			if(super.isFilteredSearch(searchString))
				return ResponseEntity
						.ok(this.getLazyTableService()
								.findAllByNameContainingAndUser(searchString,
																user, 
																pageable));
				
			Pageable sortedByPropertyAsc = PageRequest
					.of(pageable.getPageNumber(), pageable.getPageSize(),
							Sort.by("idProperty").ascending());
			return ResponseEntity.ok(this.getLazyTableService()
						.listAllPageableAndUser(sortedByPropertyAsc, user));
		} catch (ServiceException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
