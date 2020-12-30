package com.ufpr.es.divresidapi.dto;

import java.time.LocalDate;

import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.model.User;

public class EntryDTO {

	private Long id;
	private String name;
	private String description;
	private String type;
	private float amount;
	private LocalDate date;
	private boolean paid;
	private boolean collective;
	private Category category;
	private Long categoryId;
	private User user;
	
	public EntryDTO() {}
	
	public EntryDTO(
			Long id, String name, String description, 
			String type, float amount, LocalDate date, boolean paid,
			Category category, User user, boolean collective
		) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.paid = paid;
		this.category = category;
		this.categoryId = category.getId();
		this.user = user;
		this.collective = collective;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isCollective() {
		return collective;
	}
	public void setCollective(boolean collective) {
		this.collective = collective;
	}
	
}
