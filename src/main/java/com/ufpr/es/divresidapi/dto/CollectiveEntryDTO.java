package com.ufpr.es.divresidapi.dto;

import java.time.LocalDate;
import java.util.List;

import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;

public class CollectiveEntryDTO {
	
	private String name;
	private String description;
	private String type;
	private float amount;
	private LocalDate date;
	private Category category;
	private Property property;
	private List<User> residents;
	private User user;
	
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public List<User> getResidents() {
		return residents;
	}
	public void setResidents(List<User> residents) {
		this.residents = residents;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
