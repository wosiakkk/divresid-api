package com.ufpr.es.divresidapi.dto;

public class CategoryDTO {
	
	private Long id;
	private String description;
	
	public CategoryDTO() { }
	
	public CategoryDTO(Long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
