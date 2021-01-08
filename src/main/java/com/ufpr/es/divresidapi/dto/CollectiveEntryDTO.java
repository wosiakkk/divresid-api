package com.ufpr.es.divresidapi.dto;

import java.time.LocalDate;
import java.util.List;

import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.model.Entry;
import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;

public class CollectiveEntryDTO {
	
	private Long id;
	private String name;
	private String description;
	private String type;
	private float amount;
	private LocalDate date;
	private Category category;
	private Long categoryId;
	private Property property;
	private List<User> residents;
	private List<User> selectedResidents;
	private List<Entry> generatedEntries;
	private User user;
	
	
	//all atributes
	public CollectiveEntryDTO(Long id, String name, String description,
				String type, float amount, LocalDate date,
				Category category, Long categoryId, Property property,
				List<User> residents, List<User> selectedResidents,
				List<Entry> generatedEntries, User user) {
			this.id = id;
			this.name = name;
			this.description = description;
			this.type = type;
			this.amount = amount;
			this.date = date;
			this.category = category;
			this.categoryId = categoryId;
			this.property = property;
			this.residents = residents;
			this.selectedResidents = selectedResidents;
			this.generatedEntries = generatedEntries;
			this.user = user;
		}	
	
	public CollectiveEntryDTO(Long id, String name, String description, 
			String type, float amount, LocalDate date,Category category,
			Property property, List<User> residents, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.category = category;
		this.categoryId = category.getId();
		this.property = property;
		this.residents = residents;
		this.user = user;
		
	}
	
	public CollectiveEntryDTO(Long id, String name, String description, 
			String type, float amount, LocalDate date,Category category,
			Property property, User user, List<Entry> generatedEntries) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.category = category;
		this.categoryId = category.getId();
		this.property = property;
		this.user = user;
		this.generatedEntries = generatedEntries;
	}
	
	public CollectiveEntryDTO() {}

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
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setGeneratedEntries(List<Entry> generatedEntries) {
		this.generatedEntries = generatedEntries;
	}
	public List<Entry> getGeneratedEntries() {
		return generatedEntries;
	}
	public List<User> getSelectedResidents() {
		return selectedResidents;
	}
	public void setSelectedResidents(List<User> selectedResidents) {
		this.selectedResidents = selectedResidents;
	}
}
