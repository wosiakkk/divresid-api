package com.ufpr.es.divresidapi.model;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "property")
public class Property implements Serializable{

	private static final long serialVersionUID = -7509485820450038856L;

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
	
	@Size(max = 50, message = "Máximo 50 caracteres!")
	@Column
	private String landLordName;
	
	@Size(max = 50, message = "Máximo 50 caracteres!")
	@Column
	private String landLordPhone;
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 50, message = "Máximo 50 caracteres!")
	@Column
	private String zipCode;
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 150, message = "Máximo 150 caracteres!")
	@Column
	private String street;
	
	@Size(max = 150, message = "Máximo 150 caracteres!")
	@Column
	private String addressDetails;
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 50, message = "Máximo 50 caracteres!")
	@Column
	private String number;
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 150, message = "Máximo 50 caracteres!")
	@Column
	private String city;
	
	@NotBlank(message = "Campo deve ser preenchido!")
	@Size(max = 150, message = "Máximo 150 caracteres!")
	@Column
	private String state;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable( name = "property_residents",
				joinColumns = @JoinColumn(name = "property_id"),
				inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
	private List<User> residents;
	
	@Column
	private boolean active;

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

	public String getLandLordName() {
		return landLordName;
	}

	public void setLandLordName(String landLordName) {
		this.landLordName = landLordName;
	}

	public String getLandLordPhone() {
		return landLordPhone;
	}

	public void setLandLordPhone(String landLordPhone) {
		this.landLordPhone = landLordPhone;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getResidents() {
		return residents;
	}

	public void setResidents(List<User> residents) {
		this.residents = residents;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
