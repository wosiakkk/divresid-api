package com.ufpr.es.divresidapi.dto;

import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;

public class PropertyItemDTO {
	
	private Long id;
	private String name;
	private String description;
	private User owner;
	private Property property;
	
	public PropertyItemDTO(Long id, String name, String description, User owner, Property property) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.property = property;
	}
	
	public PropertyItemDTO() {}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}

}
