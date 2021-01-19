package com.ufpr.es.divresidapi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tasks")
public class Task implements Serializable{
	
	private static final long serialVersionUID = -2664454804770292453L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private boolean done;
	
	@Column
	private LocalDate date;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="target_user")
	private User targetUser;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="property_id")
	private Property property;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	
	
	public Task(Long id, String name, boolean done, LocalDate date, User targetUser, Property property,
			User user) {
		super();
		this.id = id;
		this.name = name;
		this.done = done;
		this.date = date;
		this.targetUser = targetUser;
		this.property = property;
		this.user = user;
	}

	
	public Task() {}
	
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
