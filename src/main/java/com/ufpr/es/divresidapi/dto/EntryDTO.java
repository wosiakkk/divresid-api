package com.ufpr.es.divresidapi.dto;

import java.util.Date;

import com.ufpr.es.divresidapi.model.Category;
import com.ufpr.es.divresidapi.model.User;

public class EntryDTO {

	private Long id;
	private String name;
	private String description;
	private String type;
	private Long amount;
	private Date date;
	private boolean paid;
	private Category category;
	private User user;
	
	public EntryDTO() {}
	
	public EntryDTO(
			Long id, String name, String description, 
			String type, Long amount, Date date, boolean paid,
			Category category, User user
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}