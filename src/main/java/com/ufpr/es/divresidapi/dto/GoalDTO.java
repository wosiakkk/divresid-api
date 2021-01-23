package com.ufpr.es.divresidapi.dto;

import java.time.LocalDate;

import com.ufpr.es.divresidapi.model.User;

public class GoalDTO {

	private Long id;
	private String name;
	private float value;
	private LocalDate date;
	private User user;
	
	public GoalDTO(Long id,String name, float value, LocalDate date,
			User user) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.date = date;
		this.user = user;
	}
	
	public GoalDTO() {}
	
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

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
