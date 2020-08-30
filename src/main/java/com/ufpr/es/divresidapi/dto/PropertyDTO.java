package com.ufpr.es.divresidapi.dto;

import java.util.List;

import com.ufpr.es.divresidapi.model.User;

public class PropertyDTO {
	
	private Long id;
	private String name;
	private String description;
	private String landLordName;
	private String landLordPhone;
	private String zipCode;
	private String street;
	private String addressDetails;
	private String number;
	private String city;
	private String state;
	private User user;
	private List<User> residents;
	private boolean active;
	
	
	public PropertyDTO() { }
	
	public PropertyDTO(Long id, String name, String description, String landLordName, String landLordPhone,
			String zipCode, String street, String addressDetails, String number, String city, String state, User user,
			List<User> residents, boolean active) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.landLordName = landLordName;
		this.landLordPhone = landLordPhone;
		this.zipCode = zipCode;
		this.street = street;
		this.addressDetails = addressDetails;
		this.number = number;
		this.city = city;
		this.state = state;
		this.user = user;
		this.residents = residents;
		this.active = active;
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
