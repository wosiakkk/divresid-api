package com.ufpr.es.divresidapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "property_item")
public class PropertyItem implements Serializable{

	private static final long serialVersionUID = 2311186033563905333L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 50, message = "Máximo 50 caracteres!")
	@Column
	private String name;
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 150, message = "Máximo 150 caracteres!")
	@Column
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User owner;
	
	@ManyToOne
	@JoinColumn(name = "property_id")
	@JsonIgnore
	private Property property;
	
	@ManyToOne
	@JoinColumn(name = "user_resp_id")
	private User user;
	
	@OneToOne(mappedBy = "propertyItem", optional = true)
	@JsonManagedReference
    private PropertyItemImage image;

	
	
	public PropertyItem(Long id,
			@NotBlank(message = "Campo deve ser preenchido!") @Size(max = 50, message = "Máximo 50 caracteres!") String name,
			@NotBlank(message = "Campo deve ser preenchido!") @Size(max = 150, message = "Máximo 150 caracteres!") String description,
			User owner, Property property, User user,
			PropertyItemImage image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.owner = owner;
		this.property = property;
		this.user = user;
		this.image = image;
	}

	public PropertyItem() {}
	
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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
	
	public PropertyItemImage getImage() {
		return image;
	}
	
	public void setImage(PropertyItemImage image) {
		this.image = image;
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
		PropertyItem other = (PropertyItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
