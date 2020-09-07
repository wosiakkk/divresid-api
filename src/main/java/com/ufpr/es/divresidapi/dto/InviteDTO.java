package com.ufpr.es.divresidapi.dto;

import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;

public class InviteDTO {
	
	private Long id;
	private User idFrom;
	private User idTo;
	private Property idProperty;
	private boolean accepted;
	
	public InviteDTO() {}
	
	public InviteDTO(Long id, User idFrom, User idTo, 
			Property idProperty, boolean accepted) {
		super();
		this.id = id;
		this.idFrom = idFrom;
		this.idTo = idTo;
		this.idProperty = idProperty;
		this.accepted = accepted;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getIdFrom() {
		return idFrom;
	}
	public void setIdFrom(User idFrom) {
		this.idFrom = idFrom;
	}
	public User getIdTo() {
		return idTo;
	}
	public void setIdTo(User idTo) {
		this.idTo = idTo;
	}
	public Property getIdProperty() {
		return idProperty;
	}
	public void setIdProperty(Property idProperty) {
		this.idProperty = idProperty;
	}
	public boolean isAccepted() {
		return accepted;
	}
	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}
	
}
