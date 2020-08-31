package com.ufpr.es.divresidapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

	private static final long serialVersionUID = 3083318601239931479L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 50,  message = "Máximo 50 caracteres!")
	private String name;
	
	@Size(max = 150,  message = "Máximo 150 caracteres!")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	public Category() {}

	public Category(Long id, 
			@NotBlank @Size(max = 50) String name, 
			@NotBlank @Size(max = 150) String description,
			User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
