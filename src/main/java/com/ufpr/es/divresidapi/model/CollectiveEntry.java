package com.ufpr.es.divresidapi.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "collectives")
public class CollectiveEntry implements Serializable{

	private static final long serialVersionUID = -2895455543687787013L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private float amount;
	@ManyToOne
	@JoinColumn(name = "property")
	private Property property;
	@Column
	private LocalDate date;
	@Column
	private String description;
	@Column
	private String name;
	@Column
	private String type;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
	@JoinTable(name = "collective_entries",
			   joinColumns = @JoinColumn(name = "collective_id"),
			   inverseJoinColumns = @JoinColumn(name = "entry_id"))
	private List<Entry> generatedEntries;
	
	
	public CollectiveEntry() {}

	
	public CollectiveEntry(Long id, float amount, Property property, 
			User user, LocalDate date, String description,
			String name, String type, Category category, 
			List<Entry> generatedEntries) {
		super();
		this.id = id;
		this.amount = amount;
		this.property = property;
		this.user = user;
		this.date = date;
		this.description = description;
		this.name = name;
		this.type = type;
		this.category = category;
		this.generatedEntries = generatedEntries;
	}


	public CollectiveEntry(Long id, float amount, Property property, 
			User user, LocalDate date, String description,
			String name, String type, Category category) {
		super();
		this.id = id;
		this.amount = amount;
		this.property = property;
		this.user = user;
		this.date = date;
		this.description = description;
		this.name = name;
		this.type = type;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
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
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Entry> getGeneratedEntries() {
		return generatedEntries;
	}

	public void setGeneratedEntries(List<Entry> generatedEntries) {
		this.generatedEntries = generatedEntries;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CollectiveEntry other = (CollectiveEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
