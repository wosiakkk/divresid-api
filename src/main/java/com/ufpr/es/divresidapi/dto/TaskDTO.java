package com.ufpr.es.divresidapi.dto;

import java.time.LocalDate;

import com.ufpr.es.divresidapi.model.Property;
import com.ufpr.es.divresidapi.model.User;

public class TaskDTO {

	private Long id;
	private String description;
	private boolean done;
	private LocalDate date;
	private User targetUser;
	private Property property;
	private User user;
	
	
	public TaskDTO(Long id, String description, boolean done, LocalDate date, User targetUser, Property property,
			User user) {
		super();
		this.id = id;
		this.description = description;
		this.done = done;
		this.date = date;
		this.targetUser = targetUser;
		this.property = property;
		this.user = user;
	}
	
	
	public TaskDTO() {}
	
	
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
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public User getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(User targetUser) {
		this.targetUser = targetUser;
	}
	public Property getProperty() {
		return property;
	}
	public void setProperty(Property property) {
		this.property = property;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
