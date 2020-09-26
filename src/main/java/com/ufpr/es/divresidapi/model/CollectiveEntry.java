package com.ufpr.es.divresidapi.model;

import java.util.List;

public class CollectiveEntry {

	private Long id;
	
	private double amount;
	
	private Property property;
	
	private User user;
	
	private List<Entry> generatedEntries;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
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

	public List<Entry> getGeneratedEntries() {
		return generatedEntries;
	}

	public void setGeneratedEntries(List<Entry> generatedEntries) {
		this.generatedEntries = generatedEntries;
	}
	
}
