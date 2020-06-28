package com.ufpr.es.divresidapi.dto;

import com.ufpr.es.divresidapi.model.User;

public class CategoryDTO {
	
	private Long id;
	private String name;
	private String description;
	private User user;
	
	public CategoryDTO() { }
	
	public CategoryDTO(Long id,String name, String description, User user) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
